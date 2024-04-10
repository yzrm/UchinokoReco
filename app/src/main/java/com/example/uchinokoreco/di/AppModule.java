package com.example.uchinokoreco.di;

import android.content.Context;

import com.example.uchinokoreco.data.repositories.UchinokoRecoRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class AppModule {
    @Provides
    @ViewModelScoped
    static UchinokoRecoRepository provideUchinokoRecoRepository(@ApplicationContext Context context){
        return new UchinokoRecoRepository(context);
    }
}
