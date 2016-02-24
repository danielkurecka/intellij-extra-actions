# Extra Actions for IntelliJ
This is a plugin for IntelliJ based IDEs (IDEA, PhpStorm, PyCharm, etc.) and it provides some additional actions:

### Split Selection into Lines
Adds a new caret at each line of the selection, it works the same way as in Sublime Text.<br>
Recommended shortcut: <kbd>CTRL+SHIFT+L</kbd>

### Split Selection
Splits selection into multiple carets by custom character(s).<br>
Recommended shortcut: <kbd>CTRL+SHIFT+ALT+L</kbd>

### Toggle Quotes
Switches between single and double quotes inside a string literal.
It works in PHP, JavaScript<sup>1</sup>, CoffeeScript<sup>1</sup>, Python and CSS.<br>
<sup>1</sup> Requires enabled JSIntentionPowerPack plugin<br>
Recommended shortcut: <kbd>CTRL+'</kbd>

### Break Quotes
Splits string literal at the caret position and adds concatenation
operators with the caret between them. For example:<br>
`var a = "Hello |world.";`<br>
will become<br>
`var a = "Hello " + | + "world."`.<br>
<br>It works in most languages and with multiple carets.<br>
Recommended shortcut: <kbd>ALT+.</kbd>

### Move Caret to Next/Previous Paragraph
Moves caret to the first empty line.<br>
Recommended shortcut: <kbd>CTRL+DOWN/UP</kbd>

### Move Caret to Next/Previous Paragraph with Selection
Moves caret to the first empty line with selection.<br>
Recommended shortcut: <kbd>CTRL+SHIFT+DOWN/UP</kbd>

### Select in Project
Shows current file in the project tool window.<br>
Recommended shortcut: <kbd>CTRL+SHIFT+Y</kbd>

## Installation
Inside the IDE go to _Settings|Plugins_, click _Browser Repositories..._ and search for 'Extra Actions'.

#### Shortcuts
No shortcuts are set by default. This is done to prevent conflicts.
You can easily set them by going to _Settings|Keymap|Plugins|Extra Actions_.
