# Banco de Preguntas Saber Pro - Taller Capas + MVC + Observer

Proyecto Java SE de escritorio (Swing) que implementa:
- Arquitectura en capas: presentation, domain, access, infra
- Micro patrón MVC (GUIQuestions actúa como Vista + Controlador)
- Patrón Observer (Subject en infra, dos vistas observadoras)

## Cómo compilar y ejecutar

```bash
cd src
javac -d ../bin $(find . -name "*.java")
cd ..
java -cp bin co.unicauca.bancopreguntas.presentation.GUIQuestions
```

Al ejecutar se abren 3 ventanas:
1. Ventana principal: selector de preguntas + formulario + cambio de estado.
2. Vista 1: estadísticas de preguntas por estado (se actualiza sola).
3. Vista 2: gráfica de pastel con los porcentajes (se actualiza sola).

## Estructura de paquetes

```
co.unicauca.bancopreguntas
├── presentation   (GUIQuestions, GUIObserver1, GUIObserver2)
├── domain         (Question, QuestionDistractors, QuestionService, QuestionRepository, EstadoPregunta)
├── access         (QuestionImplRepository)
└── infra          (Observer, Subject)
```

## Autor
Sebastian - Ingeniería de Sistemas, Universidad del Cauca
