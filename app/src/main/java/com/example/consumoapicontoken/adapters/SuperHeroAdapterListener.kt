package com.example.consumoapicontoken.adapters

import com.example.consumoapicontoken.Models.Superhero

interface SuperHeroAdapterListener {
    fun deleteSuperHero(superhero: Superhero)
}