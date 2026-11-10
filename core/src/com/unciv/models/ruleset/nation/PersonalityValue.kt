package com.unciv.models.ruleset.nation

import com.unciv.models.stats.Stat
import yairm210.purity.annotations.Pure
import kotlin.reflect.KMutableProperty0

/**
 * Type of Personality focus.
 *
 * Associated values in [Personality] typically ranges from 0 (no focus) to 10 (double focus). `5` is neutral.
 *
 * @property description Used for Civilopedia display
 * @property getProperty Used for indexing operators [get][Personality.get] and [set][Personality.set] in [Personality]
 * @property get Can be used to translate a [Stat] into a [PersonalityValue], e.g. `PersonalityValue[Stat.Gold]`
 */
enum class PersonalityValue(
    val description: String,
    val antidescription: String,
    internal val getProperty: Personality.() -> KMutableProperty0<Float>
) {
    // Stat focused personalities, affects the types of buildings and tile improvements the faction builds
    Production("Diligent", "Lazy", { ::production }),
    Food("Glutton", "Faster", { ::food }),
    Gold("Greedy", "Frugal",{ ::gold }),
    Science("Smart", "Stupid",{ ::science }),
    Culture("Stylish", "Dull",{ ::culture }),
    Happiness("Cheery", "Boring",{ ::happiness }),
    Faith("Faithful", "Cynical",{ ::faith }),
    // Behaviour focused personalities
    Military("Tough","Pushover", { ::military }), // Building a military but not nessesarily using it
    Aggressive("Aggressive", "Defensive",{ ::aggressive }), // How they use units agressively or defensively in wars, or their priority on war related buildings
    DeclareWar("Violent", "Pacifist",{ ::declareWar }), // Likelihood of declaring war and acceptance of warmongering, a zero means they won't declare war at all
    Commerce("Charismatic", "Shy", { ::commerce }), // Trading frequency, open borders and liberating city-states, less negative diplomacy impact
    Diplomacy("Kind", "Stubborn", { ::diplomacy }), // Likelihood of signing friendship, defensive pact, peace treaty and other diplomatic actions
    Loyal("Loyal", "Erratic", { ::loyal }), // Likelihood to make a long-lasting aliance with another civ and join wars with them
    Expansion("Ambitious", "Content", { ::expansion }), // Founding/capturing new cities, opposite of a cultural victory
    DenounceWillingness("Short-Tempered", "Stoic", { ::denounceWillingness }), // Eagerness to denounce other civs
    ;

    companion object  {
        @Pure
        operator fun get(stat: Stat): PersonalityValue {
            return when (stat) {
                Stat.Production -> Production
                Stat.Food -> Food
                Stat.Gold -> Gold
                Stat.Science -> Science
                Stat.Culture -> Culture
                Stat.Happiness -> Happiness
                Stat.Faith -> Faith
            }
        }
    }
}
