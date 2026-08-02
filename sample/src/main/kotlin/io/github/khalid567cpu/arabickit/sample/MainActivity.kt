package io.github.khalid567cpu.arabickit.sample

import android.app.Activity
import android.os.Bundle
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

        binding.inputText.setText("إِنَّ الــلَّهَ غَفُورٌ ٢٠٢٦")
        binding.runButton.setOnClickListener { renderResult() }
        renderResult()
    }

    private fun renderResult() {
        val input = binding.inputText.text.toString()
        val normalized = ArabicNormalizer.normalize(input)
        val westernDigits = ArabicNumerals.convert(input, ArabicNumeralSystem.WESTERN)
        val matches = ArabicSearch.contains(input, "ان الله")

        binding.normalizedValue.text = normalized
        binding.numeralsValue.text = westernDigits
        binding.searchValue.text = getString(
            if (matches) R.string.search_match else R.string.search_no_match,
        )
    }
}
