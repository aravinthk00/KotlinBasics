package com.example.kotlinbasics

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DiceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        (super.onCreate(savedInstanceState))
        setContentView(R.layout.diceroll_activity)


        val rollButton: Button = findViewById(R.id.rollButton)
        rollButton.setOnClickListener {
            Log.i("TAG", " Button clicked! ")
            rollDice()

//            val toast = Toast.makeText(this, "${rollDice()}", Toast.LENGTH_SHORT)
//            toast.show()
        }
        // Do a dice roll when the app starts
        //rollDice()
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice(): Int {
        // Create new Dice object with 6 sides and roll the dice
        val dice = Dice(6)
        Log.i("dice", "dice" + dice)
        val diceRoll = dice.roll()

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.diceImg)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()

        val toast = Toast.makeText(this, "${dice.roll()}", Toast.LENGTH_SHORT)
        toast.show()

        return diceRoll
    }
}

class Dice(val numSides: Int) {

    fun roll(): Int {
        Log.e("Dice", "fun roll()"+ numSides)
        return (1..numSides).random()
    }

}
