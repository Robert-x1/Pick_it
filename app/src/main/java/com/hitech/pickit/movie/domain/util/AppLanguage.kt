package com.hitech.pickit.movie.domain.util

enum class AppLanguage(val displayName: String, val code: String) {
    ENGLISH("English", "en"),
    ARABIC("العربية", "ar");

    companion object {
        fun getByCode(code: String): AppLanguage {
            return entries.find { it.code == code } ?: ENGLISH
        }
    }
}