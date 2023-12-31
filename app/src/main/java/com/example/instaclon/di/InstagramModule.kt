package com.example.instaclon.di

import com.example.instaclon.data.AuthenticationRepositoryImpl
import com.example.instaclon.domain.repository.AuthenticationRepository
import com.example.instaclon.domain.use_cases.AuthenticationUseCases
import com.example.instaclon.domain.use_cases.FirebaseAuthState
import com.example.instaclon.domain.use_cases.FirebaseSignIn
import com.example.instaclon.domain.use_cases.FirebaseSignOut
import com.example.instaclon.domain.use_cases.FirebaseSignUp
import com.example.instaclon.domain.use_cases.IsUserAuthenticated
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InstagramModule {
    @Singleton
    @Provides
    fun provideFirebaseAuthentication():FirebaseAuth{
        return FirebaseAuth.getInstance()

    }
    @Singleton
    @Provides
    fun provideFirebaseFirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }
    @Singleton
    @Provides
    fun provideFirebaseStorage():FirebaseStorage{
        return FirebaseStorage.getInstance()
    }
    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth:FirebaseAuth,firestore: FirebaseFirestore): AuthenticationRepository{
     return AuthenticationRepositoryImpl(auth=auth, firestore=firestore)
    }
    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository)=AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository=repository),
        firebaseAuthState = FirebaseAuthState(repository=repository),
        firebaseSignOut = FirebaseSignOut(repository=repository),
        firebaseSignIn = FirebaseSignIn(repository=repository),
        firebaseSignUp = FirebaseSignUp(repository=repository)
    )
}











