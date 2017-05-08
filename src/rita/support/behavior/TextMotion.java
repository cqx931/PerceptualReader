package rita.support.behavior;

import rita.RiText;

// TODO: factor out this class
public abstract class TextMotion extends InterpolatingBehavior
{
  protected float wwyw = 0;  // wiggle while you wait (for NTM only) 
  
  public TextMotion(RiText rt, float startTimeOffset, float duration) {
    super(rt, startTimeOffset, duration); 
  }
  
  public void update() 
  { 
    if (duration <= 0 || completed || isPaused())  
      return;
    
    // wiggle crap (tmp)
    if (wwyw > 0 && isWaiting()) {
      if (Math.random() < .333) {
        rt.x += Math.random() > .5 ? -.5 : .5;
        rt.y += .5;
        interpolater.setStart(rt.getPosition());
      }
      return;
    }
    
    if (interpolater.update()) // true if running 
    {
      if (!initd) {
        initd = true; // do once
        getStartValueFromParent(rt, interpolater);
      }
      updateParentValues(rt, interpolater.getValues());
    }
    checkForCompletion();
  }

  /**
   * @invisible
   */  
  public float getWiggle() {
    return this.wwyw;
  }

  /**
   * @invisible
   */
  public void setWiggle(float wiggle) {
    this.wwyw = wiggle;
  }
 
}// end
