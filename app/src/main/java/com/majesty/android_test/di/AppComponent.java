package com.majesty.android_test.di;


import com.majesty.android_test.repository.CatchRepository;
import com.majesty.android_test.repository.DetailRepository;
import com.majesty.android_test.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    void injectMainRepository(MainRepository mainRepository);

    void injectDetailRepository(DetailRepository detailRepository);

    void injectCatchRepository(CatchRepository catchRepository);
}
