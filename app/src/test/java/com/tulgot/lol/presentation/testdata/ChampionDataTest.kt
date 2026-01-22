package com.tulgot.lol.presentation.testdata

import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.domain.model.Image
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.model.Spell

object ChampionDataTest {
    fun getMockData() = ChampionResponse(
            type = "champion",
            format = "standAloneComplex",
            version = "15.7.1",
            data = listOf(
                Champion(
                    blurb = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
                    id = "Aatrox",
                    image = Image(
                        full = "Aatrox.png",
                        group = "champion"
                    ),
                    key = "266",
                    lore = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find freedom once more, corrupting and transforming those foolish enough to try and wield the magical weapon that contained his essence. Now, with stolen flesh, he walks Runeterra in a brutal approximation of his previous form, seeking an apocalyptic and long overdue vengeance.",
                    name = "Aatrox",
                    passive = Passive(
                        description = "Periodically, Aatrox's next basic attack deals bonus <magicDamage>magic damage</magicDamage> and heals him, based on the target's max health.",
                        image = Image(
                            full = "Aatrox_Passive.png",
                            group = "passive"
                        ),
                        name = "Deathbringer Stance"
                    ),
                    spells = listOf(
                        Spell(
                            description = "Aatrox slams his greatsword down, dealing physical damage. He can swing three times, each with a different area of effect.",
                            id = "AatroxQ",
                            image = Image(
                                full = "AatroxQ.png",
                                group = "spell"
                            ),
                            name = "The Darkin Blade"
                        ),
                        Spell(
                            description = "Aatrox smashes the ground, dealing damage to the first enemy hit. Champions and large monsters have to leave the impact area quickly or they will be dragged to the center and take the damage again.",
                            id = "AatroxW",
                            image = Image(
                                full = "AatroxW.png",
                                group = "spell"
                            ),
                            name = "Infernal Chains"
                        )
                    ),
                    tags = listOf(
                        "Figther"
                    ),
                    title = "The Darkin Blade"
                ),
                Champion(
                    blurb = "Innately connected to the magic of the spirit realm, Ahri is a fox-like vastaya who can manipulate her prey's emotions and consume their essence—receiving flashes of their memory and insight from each soul she consumes. Once a powerful yet wayward...",
                    id = "Ahri",
                    image = Image(
                        full = "Ahri.png",
                        group = "champion"
                    ),
                    key = "103",
                    lore = "",
                    name = "Ahri",
                    passive = Passive(
                        description = "After killing 9 minions or monsters, Ahri heals.<br>After taking down an enemy champion, Ahri heals for a greater amount.",
                        image = Image(
                            full = "Ahri_SoulEater2.png",
                            group = "passive"
                        ),
                        name = "Essence Theft"
                    ),
                    spells = listOf(
                        Spell(
                            description = "Ahri sends out and pulls back her orb, dealing magic damage on the way out and true damage on the way back.",
                            id = "AhriQ",
                            image = Image(
                                full = "AhriQ.png",
                                group = "spell",
                            ),
                            name = "Orb of Deception"
                        ),
                        Spell(
                            description = "Ahri gains a brief burst of Move Speed and releases three fox-fires, that lock onto and attack nearby enemies.",
                            id = "AhriW",
                            image = Image(
                                full = "AhriW.png",
                                group = "spell",
                            ),
                            name = "Fox-Fire"
                        )
                    ),
                    tags = listOf(
                        "Mage",
                        "Assassin"
                    ),
                    title = "The Nine-Tailed Fox"
                )
            )
        )

}