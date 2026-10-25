package com.jepongdevxyz.devxyzshield.di

import android.content.Context
import androidx.room.Room
import com.jepongdevxyz.devxyzshield.data.local.db.DevxyzShieldDao
import com.jepongdevxyz.devxyzshield.data.local.db.DevxyzShieldDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDevxyzShieldDatabase(
        @ApplicationContext context: Context
    ): DevxyzShieldDatabase {
        return Room.databaseBuilder(
            context,
            DevxyzShieldDatabase::class.java,
            DevxyzShieldDatabase.DATABASE_NAME
        )
            .addMigrations(DevxyzShieldDatabase.MIGRATION_1_2)
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Provides
    @Singleton
    fun provideDevxyzShieldDao(database: DevxyzShieldDatabase): DevxyzShieldDao {
        return database.devxyzShieldDao()
    }
}
