import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dot  extends Actor
{
    private int x;
    private int y;
    private int d;
    private final int DOT_SIZE=20;
    
    public Dot (int dot){
        GreenfootImage image1 = new GreenfootImage("SnakeHead.gif");
        image1.mirrorHorizontally();
        d = dot;
        if(d == 0){
            setImage(image1);
        } else {
            setImage("close.png");
        }
    }
    /**
     * Act - do whatever the Head wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if ( d == 0 ){
            lead();
            lookForFood();
            lookForEdge();
            lookForDot();
        }
        else
        {
            follow();
        }
        
    }   
    
    
    /**
     * lead controls the movement of the head of our snake
     * @param THere are no parameters
     * retuen There is nothing to return
     */
    private void lead()
    {
        double angle;
        SnakeWorld myWorld = (SnakeWorld)getWorld();
        x = getX();
        y = getY();
        
        if( Greenfoot.isKeyDown("left") )
        {
            setRotation(180);
        }
        else if( Greenfoot.isKeyDown("right") )
        {
            setRotation(0);
        }
        else if( Greenfoot.isKeyDown("down") )
        {
            setRotation(90);
        }
        else if( Greenfoot.isKeyDown("up") )
        {
            setRotation(270);
        }
        
        angle = Math.toRadians( getRotation() );
        x = (int) Math.round( getX() + Math.cos(angle) * DOT_SIZE);
        y = (int) Math.round( getY() + Math.sin(angle) * DOT_SIZE);
        
        setLocation(x, y);
        myWorld.setDX(d, x);
        myWorld.setDY(d, y);
    }
    
    private void lookForEdge()
    {
        if( isAtEdge() )
        {
            getWorld().showText("You do realise you cant touch the wall", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }
    
    /**
     * lookforFood checks if our snake is touching food adn then grows its tail if we have eaten foods
     * @param There are no parameters
     * @return noting is returned
     */
    private void lookForFood()
    {
        SnakeWorld world = (SnakeWorld)getWorld();
        
        if( isTouching(Food.class) )
        {
            getWorld().removeObject( getOneIntersectingObject(Food.class) );
            growTail();
            world.addFood();
        }
    }
    
    /**
     * lookForDot will check if the snake has hit it's body of our world and end the game
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void lookForDot()
    {
        if( isTouching(Dot.class) )
        {
            getWorld().showText("You suck and should stop playing this game because\nyou're just terrible. Please press the 'x' button!", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }
    
    /**
     * Follow handles the movement for every body part of the snake
     * @param There are no parameters
     * @return nothing is returned
     */
    private void follow()
    {
        SnakeWorld myWorld = (SnakeWorld)getWorld();
        x = myWorld.getMyX(d);
        y = myWorld.getMyY(d);
        setLocation(x, y);
        
    }
    
    /**
     * growTail will add a dot to the end of our snake when we eat a food object
     * @param There are no parameters
     * @return nothing is returned
     */
    private void growTail()
    {
        SnakeWorld world = (SnakeWorld)getWorld();
        world.addDot();
    }
    
}
