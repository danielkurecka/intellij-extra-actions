# Extra Actions for IntelliJ
This is a plugin for IntelliJ based IDEs (IDEA, PhpStorm, PyCharm, etc.) and it provides some additional actions:

### Split Selection into Lines
Adds a new caret at each line of the selection, it works the same way as in Sublime Text. 
Recommended shortcut: <kbd>Ctrl+Shift+L</kbd>

### Split Selection
Splits selection into multiple carets by custom character(s). 
Recommended shortcut: <kbd>Ctrl+Shift+Alt+L</kbd>

### Toggle Quotes
Switches between single and double quotes inside a string literal. It works in most languages and with multiple carets. For JavaScript/CoffeeScript, the "JavaScript Intention Power Pack" plugin must be enabled. 
Recommended shortcut: <kbd>Ctrl+'</kbd>

### Break Quotes
Splits a string literal at the caret position and adds concatenation operators with the caret between them. For example: 
`var a = "Hello |world.";` 
will become 
`var a = "Hello " + | + "world."`. 
 It works in most languages and with multiple carets. 
Recommended shortcut: <kbd>Ctrl+.</kbd>

### Run This
This action is equivalent to right-clicking a file and selecting "Run". It bypasses any dialogs and immediately runs the current file/context. 
Recommended shortcut: <kbd>F5</kbd>

### Debug This
This action is equivalent to right-clicking a file and selecting "Debug". It bypasses any dialogs and immediately debugs the current file/context. 
Recommended shortcut: <kbd>Shift+F5</kbd>

### Select in Project
Shows the current file in the Project tool window. 
Recommended shortcut: <kbd>Ctrl+Shift+Y</kbd>

### Search Online
Searches the selection or the word at the caret via Google. 
Recommended shortcut: <kbd>Shift+F1</kbd>

### Move Caret to Next/Previous Paragraph
Moves the caret to the first empty line. 
Recommended shortcut: <kbd>Ctrl+Down/Up</kbd>

### Move Caret to Next/Previous Paragraph with Selection
Moves the caret to the first empty line while keeping the selection. 
Recommended shortcut: <kbd>Ctrl+Shift+Down/Up</kbd>

## Installation
Inside the IDE go to _Settings | Plugins_, click _Marketplace_ and search for 'Extra Actions'.

**Note about shortcuts** 
No keyboard shortcuts are set by default to avoid conflicts. You can easily configure them by going to: 
_Settings | Keymap | Plugins | Extra Actions_.

#### License
Apache 2.0
