package com.wiryadev.productpagingrepro.data.local

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesSecondhandDatabase(
        @ApplicationContext context: Context,
    ): SecondhandDatabase = Room
        .databaseBuilder(
            context,
            SecondhandDatabase::class.java,
            "secondhand_db"
        )
        .build()

}

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindsProductLocalDataSource(
        productLocalDataSourceImpl: ProductLocalDataSourceImpl
    ): ProductLocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    @Singleton
    fun provideProductCacheDao(
        database: SecondhandDatabase
    ): ProductCacheDao = database.productCacheDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(
        database: SecondhandDatabase
    ): RemoteKeysDao = database.remoteKeysDao()


}