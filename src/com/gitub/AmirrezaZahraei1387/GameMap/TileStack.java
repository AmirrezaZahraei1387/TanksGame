package com.gitub.AmirrezaZahraei1387.GameMap;


public class TileStack {
    public TileGB[] stack;

    public TileStack(TileGB[] t){

        if(t == null)
            throw new NullPointerException("the tile stack can not be null.");

        stack = t;
    }

    public void add(TileGB t){
        TileGB[] temp = new TileGB[stack.length + 1];

        System.arraycopy(stack, 0, temp, 0, stack.length);

        temp[stack.length] = t;
    }

    public void set(TileGB t, int l){
        if(l < stack.length) {
            stack[l] = t;
        }else{

            TileGB[] temp = new TileGB[l + 1];

            System.arraycopy(stack, 0, temp, 0, stack.length);
            temp[l] = t;
        }
    }
}
