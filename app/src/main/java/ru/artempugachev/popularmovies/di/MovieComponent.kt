package ru.artempugachev.popularmovies.di

import com.squareup.picasso.Picasso
import dagger.Component

/**
 * Provides dependencies for loading movies
 * */
@AppScope
@Component(modules=[PicassoModule::class])
interface MovieComponent {
    fun getPicasso(): Picasso
}