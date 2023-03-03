package com.example.calcexam

import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.size

class AddClickListener {

    fun add(id: List<TableRow>, resultTv: TextView, stackTv: TextView) {
        var count = 0
        val numberArray = ArrayList<String>()
        val signArray = ArrayList<String>()

        for (i: Int in 0 until (id.size)) {
            for (j: Int in 0 until (id[i].size)) {
                id[i][j].setOnClickListener { key ->
                    key.requestFocus()
                    try {
                        val keyText = key as TextView
                        keyText.text.toString().let { tvText ->
                            when (checksum(tvText)) {
                                0 -> {
                                    if (resultTv.text.isNotEmpty() && resultTv.text[0].toString() == "0") {
                                        if (keyText.text.toString() != "0") {
                                            resultTv.text = null
                                            count++
                                            resultTv.append(tvText)
                                        }
                                    } else {
                                        count++
                                        resultTv.append(tvText)
                                    }
                                }
                                1 -> {
                                    if (count > 0) {
                                        signArray.add(tvText)
                                        numberArray.add(
                                            resultTv.text.substring(
                                                resultTv.text.length - count,
                                                resultTv.text.length
                                            )
                                        )
                                        resultTv.append(tvText)
                                        count = 0
                                    }
                                }

                                2 -> {
                                    if (count > 0) {
                                        numberArray.add(
                                            resultTv.text.substring(
                                                resultTv.text.lastIndex - count + 1,
                                                resultTv.text.lastIndex + 1
                                            )
                                        )
                                        count = 0
                                    }
                                    if (numberArray.size != 0 && signArray.size != 0) {
                                        stackTv.text = resultTv.text.toString()
                                    }
                                }

                                3 -> {
                                    count = 0
                                    numberArray.clear()
                                    signArray.clear()
                                    resultTv.text = "0"
                                }
                            }
                        }
                    } catch (e: ClassCastException) {
                        if (resultTv.length() != 0) {
                            when (checksum(resultTv.text.last().toString())) {
                                0 -> {
                                    resultTv.text =
                                        resultTv.text.substring(0, resultTv.length() - 1)
                                    if (numberArray.size != 0) {
                                        numberArray[numberArray.size - 1] =
                                            resultTv.text.toString()
                                    }
                                }
                                1 -> {
                                    resultTv.text =
                                        resultTv.text.substring(0, resultTv.length() - 1)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checksum(s: String): Int {
        return when (s) {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> {
                0
            }
            "÷", "×", "-", "+" -> {
                1
            }
            "=" -> {
                2
            }
            "C" -> {
                3
            }
            else -> {
                -1
            }
        }
    }
}