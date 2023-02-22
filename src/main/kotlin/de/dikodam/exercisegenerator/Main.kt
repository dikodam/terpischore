package de.dikodam.exercisegenerator

import de.dikodam.exercisegenerator.BaseChord.*
import kotlin.random.Random

fun main() {

    fun getRandChord(): BaseChord =
        BaseChord.values()[Random.nextInt(0, BaseChord.values().size)]

    fun getRandJethroForm(): Int =
        Random.nextInt(0, 3) * 2 + 1

    val generatePitch = generateSequence { getRandChord() }
    val generateJethroForm = generateSequence { getRandJethroForm() }
    val exercises = generateExercises(generatePitch, generateJethroForm, 16, setOf(Gm,Ab, B, Cm, D, Eb))
    println(exercises)
}

private fun generateExercises(
    generatePitch: Sequence<BaseChord>,
    generateJethroForm: Sequence<Int>,
    amount: Int = 50,
    constrainToChords: Set<BaseChord> = BaseChord.values().toSet()
): List<BasicTriadChordExercise> =
    generatePitch.zip(generateJethroForm)
        .map { (pitch, chord) -> BasicTriadChordExercise(pitch, chord) }
        .filter { constrainToChords.contains(it.basePitch) }
        .take(amount)
        .toList()


data class BasicTriadChordExercise(val basePitch: BaseChord, val form: Int) {
    override fun toString(): String {
        return "${basePitch.displayName} - $form"
    }
}

