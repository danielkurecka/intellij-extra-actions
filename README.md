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


## Developing
The plugin currently uses the old "DevKit" approach.

### Step-by-step instructions to develop/compile the plugin from source:

1. **Clone this repository or your fork:**
	```bash
	git clone https://github.com/danielkurecka/intellij-extra-actions
	```

2. **Download external libraries:**
	```bash
	cd extra-actions
	./external/download.sh
	```

3. **Open the cloned directory in IntelliJ Community Edition.**

4. **Install the "Plugin DevKit" plugin:**
Go to _File | Settings | Plugins_ and install the "Plugin DevKit" plugin.

5. **Set up the SDK:**
	- Go to _File | Project Structure | Project_.
	- In the SDK dropdown, choose **IntelliJ Community Edition SDK**.
	- If no SDK is available:
		- Select _Add IntelliJ Platform Plugin SDK from disk..._.
		- Choose the directory of your IntelliJ Community Edition installation.

6. **Build the project:**
	- To build the project, go to _Build | Build Project_.
	- To run the plugin, go to _Run | Run 'Plugin'_, which will run the plugin within your IntelliJ Community Edition.

7. **Run the plugin in another IntelliJ IDE:**
	- Go to _Run | Edit Configurations | Plugin_
	- For the JRE, select the installation directory of your target IntelliJ IDE.

8. **Create the plugin JAR file:**
	- Go to _Build | Prepare Module 'extra-actions' for Deployment_.
	- This will generate the plugin JAR file, which can be manually loaded via _Settings | Plugins | Install Plugin from Disk_

## License
Apache 2.0
