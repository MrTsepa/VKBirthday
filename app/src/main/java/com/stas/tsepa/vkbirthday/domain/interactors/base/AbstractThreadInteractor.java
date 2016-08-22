package com.stas.tsepa.vkbirthday.domain.interactors.base;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public abstract class AbstractThreadInteractor implements Interactor {

    public abstract void run();

    @Override
    public void execute() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                AbstractThreadInteractor.this.run();
            }
        });
        thread.start();
    }
}
