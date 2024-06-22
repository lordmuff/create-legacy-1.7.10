package com.siepert.createlegacy.util.compat.jei.recipe;

import com.siepert.createlegacy.util.Reference;
import com.siepert.createlegacy.util.compat.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class WashingRecipeCategory extends AbstractWashingRecipeCategory<WashingRecipe> {
    private final IDrawable background;
    private final String name;


    @Override
    public String getUid() {
        return RecipeCategories.WASHING_BY_FAN;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getModName() {
        return Reference.NAME;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WashingRecipe recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(input, true, 2, 8);
        stacks.init(output, false, 27, 8);
        stacks.init(outputOptional, false, 46, 8);
        stacks.set(ingredients);
    }

    public WashingRecipeCategory(IGuiHelper helper) {
        super(helper);
        background = helper.createDrawable(TEXTURES, 0, 0, 64, 32);
        name = "Washing by Fan";
    }
}
