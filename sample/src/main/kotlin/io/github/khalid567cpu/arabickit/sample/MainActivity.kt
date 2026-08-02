package io.github.khalid567cpu.arabickit.sample

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import io.github.khalid567cpu.arabickit.ArabicHighlighter
import io.github.khalid567cpu.arabickit.ArabicNormalizer
import io.github.khalid567cpu.arabickit.ArabicNumeralSystem
import io.github.khalid567cpu.arabickit.ArabicNumerals
import io.github.khalid567cpu.arabickit.ArabicSearch
import io.github.khalid567cpu.arabickit.sample.databinding.ActivityMainBinding

public class MainActivity : Activity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputText.setText("إِنَّ الــلَّهَ غَفُورٌ، إن الله رحيم ٢٠٢٦")
        binding.runButton.setOnClickListener { renderResult() }
        renderResult()
    }

    private fun renderResult() {
        val input = binding.inputText.text.toString()
        val query = "ان الله"
        val normalized = ArabicNormalizer.normalize(input)
        val westernDigits = ArabicNumerals.convert(input, ArabicNumeralSystem.WESTERN)
        val ranges = ArabicSearch.findAll(input, query)

        binding.normalizedValue.text = normalized
        binding.numeralsValue.text = westernDigits
        binding.searchValue.text = resources.getQuantityString(
            R.plurals.search_matches,
            ranges.size,
            ranges.size,
        )
        binding.highlightedValue.text = ArabicHighlighter.highlightBackground(
            text = input,
            query = query,
            color = Color.YELLOW,
        )
    }
}
