// license-header java merge-point
//
// Generated by: SessionListener.vsl in andromda-ejb3-cartridge.
//
package org.andromda.demo.ejb3.animal;

/**
 * Callback Listener for Session bean org.andromda.demo.ejb3.animal.AnimalService
 *
 * @see org.andromda.demo.ejb3.animal.AnimalService
 */
public class AnimalServiceListener 
{
    /**
     * Default public no-args constructor
     */
    public AnimalServiceListener() 
    {
        // empty constructor
    }

    @javax.annotation.PostConstruct
    public void postConstruct(javax.interceptor.InvocationContext ctx) 
    {
        try
        {
            System.out.println("AnimalServiceListener: postConstruct");
            ctx.proceed();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @javax.annotation.PreDestroy
    public void preDestroy(javax.interceptor.InvocationContext ctx) 
    {
        try
        {
            System.out.println("AnimalServiceListener: preDestroy");
            ctx.proceed();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}