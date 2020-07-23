package com.example.musictheory

class LessonList
{
    var modesSlides = listOf<Slide>(
                                    Slide("The white keys on a piano make up the C-major scale. The Major scale is also known as the Ionian Mode."),
                                    Slide("If you play the white keys from D to D, it becomes a different 'Mode', The Dorian Mode."),
                                    Slide("Each note in the scale is the start of a different mode."),
                                    Slide("The modes include: Ionian, Dorian, Phrygian, Lydian, Mixolydian, Aolean and Locrian.")
                                    )


    var rhythmSlides = listOf<Slide>(
                                    Slide("Rhythm is the pattern of weak and strong beats that make up music."),
                                    Slide("Rhythm is built on sections where time is split in to halves, 3rds, 4ths, 8ths, 16ths, or other simple ratios."),
                                    Slide("You may think of Drums when you think of rhythm, but almost any instrument can produce a rhythm."),
                                    Slide("Finding the rhythm in a song is as easy as clapping to the beat.")
                                    )

    var circleSlides = listOf<Slide>(
                                    Slide("Circle of Fifths allows you to know which key to play in"),
                                    Slide("You complete the circle by finding the 5th note in a scale, then the fifth of that scale, until you reach the first note again."),
                                    Slide("Count the number of sharps or flats you see at the beginning of a music sheet. That's how many steps around the circle you go."),
                                    Slide("Go to the right for sharps and the left for flats. Now you've found the key!")
                                    )

    var harmonySlides = listOf<Slide>(
                                        Slide("Harmony is when the frequencies of two sounds sound pleasant together."),
                                        Slide("Its usually when two sine waves have frequencies which are small integer ratios of each other."),
                                        Slide("Starting at 'C', the next 'G' vibrates 1.5 times faster, and the next C vibrates twice as fast."),
                                        Slide("These notes sound harmonic or 'consonant' because of the simple ratios. Dissonant notes have larger integer ratios like 13/12")
                                    )


    var lessonList = listOf<LessonObject>(

        LessonObject("Rhythm","rhythm",rhythmSlides, 0),
        LessonObject("Harmony","harmony",harmonySlides, 1),
        LessonObject("Modes","modes",modesSlides, 2),
        LessonObject("Circle Of Fifths","circle",circleSlides, 3)
    )


}