package destiny.thornsinyou.block;

import destiny.thornsinyou.block.entity.CopperKettleBlockEntity;
import destiny.thornsinyou.block.state.CopperKettleSupport;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import destiny.thornsinyou.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;

public class CopperKettleBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<CopperKettleSupport> SUPPORT = EnumProperty.create("support", CopperKettleSupport.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 7.0D, 13.0D);
    protected static final VoxelShape SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0D, -1.0D, 0.0D, 16.0D, 0.0D, 16.0D));

    public CopperKettleBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SUPPORT, CopperKettleSupport.NONE).setValue(WATERLOGGED, false));
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (heldStack.isEmpty() && player.isShiftKeyDown()) {
            level.setBlockAndUpdate(pos, state.setValue(SUPPORT, state.getValue(SUPPORT).equals(CopperKettleSupport.HANDLE)
                    ? getTrayState(level, pos) : CopperKettleSupport.HANDLE));
            level.playSound(null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);
        } else if (!level.isClientSide) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof CopperKettleBlockEntity copperKettleEntity) {
                ItemStack servingStack = copperKettleEntity.useHeldItemOnBrew(heldStack);
                if (servingStack != ItemStack.EMPTY) {
                    if (!player.getInventory().add(servingStack)) {
                        player.drop(servingStack, false);
                    }
                    level.playSound(null, pos, SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.BLOCKS, 1.0F, 1.0F);
                } else {
                    NetworkHooks.openScreen((ServerPlayer) player, copperKettleEntity, pos);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(SUPPORT).equals(CopperKettleSupport.TRAY) ? SHAPE_WITH_TRAY : SHAPE;
    }

    private CopperKettleSupport getTrayState(LevelAccessor level, BlockPos pos) {
        if (level.getBlockState(pos.below()).is(ModTags.TRAY_HEAT_SOURCES)) {
            return CopperKettleSupport.TRAY;
        }
        return CopperKettleSupport.NONE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        FluidState fluid = level.getFluidState(context.getClickedPos());

        BlockState state = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);

        if (context.getClickedFace().equals(Direction.DOWN)) {
            return state.setValue(SUPPORT, CopperKettleSupport.HANDLE);
        }
        return state.setValue(SUPPORT, getTrayState(level, pos));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (facing.getAxis().equals(Direction.Axis.Y) && !state.getValue(SUPPORT).equals(CopperKettleSupport.HANDLE)) {
            return state.setValue(SUPPORT, getTrayState(level, currentPos));
        }
        return state;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        ItemStack stack = super.getCloneItemStack(level, pos, state);
        CopperKettleBlockEntity cookingPotEntity = (CopperKettleBlockEntity) level.getBlockEntity(pos);
        if (cookingPotEntity != null) {
            CompoundTag nbt = cookingPotEntity.writeBrew(new CompoundTag());
            if (!nbt.isEmpty()) {
                stack.addTagElement("BlockEntityTag", nbt);
            }
            if (cookingPotEntity.hasCustomName()) {
                stack.setHoverName(cookingPotEntity.getCustomName());
            }
        }
        return stack;
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof CopperKettleBlockEntity cookingPotEntity) {
                Containers.dropContents(level, pos, cookingPotEntity.getDroppableInventory());
                cookingPotEntity.getUsedRecipesAndPopExperience(level, Vec3.atCenterOf(pos));
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof CopperKettleBlockEntity) {
                ((CopperKettleBlockEntity) tileEntity).setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, SUPPORT, WATERLOGGED);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof CopperKettleBlockEntity cookingPotEntity && cookingPotEntity.isHeated()) {
            SoundEvent boilSound = !cookingPotEntity.getBrew().isEmpty()
                    ? ModSounds.BLOCK_COOKING_POT_BOIL_SOUP.get()
                    : ModSounds.BLOCK_COOKING_POT_BOIL.get();
            double x = (double) pos.getX() + 0.5D;
            double y = pos.getY();
            double z = (double) pos.getZ() + 0.5D;
            if (random.nextInt(10) == 0) {
                level.playLocalSound(x, y, z, boilSound, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.2F + 0.9F, false);
            }
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof CopperKettleBlockEntity) {
            ItemStackHandler inventory = ((CopperKettleBlockEntity) tileEntity).getInventory();
            return MathUtils.calcRedstoneFromItemHandler(inventory);
        }
        return 0;
    }


    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.COPPER_KETTLE.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        if (level.isClientSide) {
            return createTickerHelper(blockEntity, ModBlockEntityTypes.COPPER_KETTLE.get(), CopperKettleBlockEntity::animationTick);
        }
        return createTickerHelper(blockEntity, ModBlockEntityTypes.COPPER_KETTLE.get(), CopperKettleBlockEntity::brewingTick);
    }
}
