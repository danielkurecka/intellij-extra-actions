## Extra Actions
This plugin for intellij IDEs provides some additional actions:

#### Split Selection into Lines
Adds new caret at each line of the selection, works the same way as in Sublime Text.
Recommended shortcut: <kbd>CTRL+SHIFT+L</kbd>

#### Split Selection
Splits selection into multiple carets by custom character(s).
Recommended shortcut: <kbd>CTRL+SHIFT+ALT+L</kbd>

#### Toggle Quotes
Switches between single and double quotes inside a string literal.
It works in PHP, JavaScript (requires enabled JSIntentionPowerPack plugin) and CSS.
Recommended shortcut: <kbd>CTRL+'</kbd>

#### Break Quotes
Splits string literal at caret position, adds concatenation
operators and puts the caret between them. For example:
`var a = "Hello |world.";` becomes
`var a = "Hello " + | + "world."`. It works in most languages
and with multiple carets. Recommended shortcut: <kbd>ALT+.</kbd>

#### Move Caret to Next/Previous Paragraph
Moves caret to first empty line.
Recommended shortcut: <kbd>CTRL+UP/DOWN</kbd>

#### Move Caret to Next/Previous Paragraph with Selection
Moves caret to first empty line with selection.
Recommended shortcut: <kbd>CTRL+SHIFT+UP/DOWN</kbd>

#### Select in Project
Show current file in project tool window.
Recommended shortcut: <kbd>CTRL+SHIFT+Y</kbd>

* * *

##### Shortcuts
No shortcuts are set by default. This is done to prevent conflicts.
You can easily set them by going to _Settings|Keymap|Plugins|Extra Actions_.
