import game.dev.birds.bird;
import game.dev.blocks.blocks;
import game.dev.pigs.pigs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testClass{
    @Test
    public void test1(){
        blocks block = new blocks(100);
        assertEquals(100, block.getHealth());
    }
    @Test
    public void test2(){
        pigs smallpig = new pigs(200);
        assertEquals(200, smallpig.getHealth());
    }
    @Test
    public void test3(){
        bird chidiya = new bird(300);
        assertEquals(300, chidiya.getPower());
    }
}

