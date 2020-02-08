package com.margge.dogami.presentation.main

import com.margge.domain.Game

fun getMockedGame(): Game {
    return Game(
        1,
        "Hombres Lobo De Castronegro",
        8,
        18,
        "En general, existen dos bandos en la partida: aldeanos y hombres lobo",
        "10-30 minutos",
        "media",
        "10",
        "https://i.imgur.com/qQwtiPG.png",
        "https://i.imgur.com/WCQBQf7.jpg",
        false
    )
}