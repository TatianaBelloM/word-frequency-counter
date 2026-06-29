# Word Frequency Counter

Algoritmo que lee un archivo de texto, cuenta la frecuencia de cada palabra y construye un histograma ordenado de mayor a menor ocurrencia.

## Ejecución

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.lumu.qa.WordFrequencyApp" -Dexec.args="input.txt"
```

## Complejidad del algoritmo

| Etapa | Complejidad |
|---|---|
| Tokenización | O(n) |
| Conteo de frecuencias (HashMap) | O(n) |
| Ordenamiento (Merge Sort) | O(n log n) |

Donde **n** = número de palabras del texto.

## Enunciado del problema

> Knowing how often a word appears in a sentence or block of text is helpful for several types of word analysis. Using your preferred language/framework, create your own algorithm that reads a text file and counts the frequency of words in the given text. Then it counts words and characters, and also constructs a histogram displaying the words and their frequency.
>
> **Restrictions:** You may not use any native sorting functions provided by your language or its standard library (e.g. no `sorted()`, no `.sort()`, no `Collections.sort()`, or equivalent). You must implement your own sorting logic from scratch.
>
> **Example input:**
> ```
> lumu lumu lumu lumu lumu illuminates illuminates attacks and adversaries
> lumu illuminates all attacks and adversaries
> ```
>
> **Expected output:**
> ```
> 16 words
> 116 characters
> lumu: 6
> illuminates: 3
> attacks: 2
> adversaries: 2
> and: 1
> all: 1
> ```
>
> **Requirements:** Ensure that the output arranges the words by their number of occurrences in descendent order. Explain what is the computational complexity of your algorithm.

## Asunciones

- **Conteo de caracteres:** se cuenta el total de caracteres del archivo incluyendo espacios y saltos de línea (`\n`). El ejemplo del enunciado muestra 116 caracteres, lo cual solo es consistente si los saltos de línea no se cuentan; sin embargo, por instrucción explícita se cuentan, por lo que el resultado para el archivo de ejemplo será diferente al mostrado.
- **Frecuencia de "and":** el enunciado muestra `and: 1` pero la palabra aparece en ambas líneas del texto de ejemplo. Esta implementación la cuenta correctamente como 2.
- **Normalización:** las palabras se convierten a minúsculas y se eliminan todos los caracteres no alfabéticos (puntos, comas, comillas, etc.) antes de contar.
