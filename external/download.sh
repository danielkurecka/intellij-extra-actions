#!/bin/bash

# This script downloads plugins that are necessary in order to build this plugin,
# they are needed only for the compilation and are not bundled within the plugin.

script_dir=$(dirname "$(realpath "$0")")
cd "$script_dir"

download_and_extract() {
	local url="$1"
	local path_in_zip="$2"
	local output_file=$(basename "$path_in_zip")

	# download the zip file
	echo "Downloading $url"
	curl -s -L "$url" -o tmp.zip

	# extract the specified file from the zip
	echo "Extracting $output_file"
	unzip -q -p tmp.zip "$path_in_zip" > "$output_file"

	rm tmp.zip
}

# The links point to versions for 2024.1.2, but the exact version shouldn't matter

# PHP https://plugins.jetbrains.com/plugin/6610-php/versions
download_and_extract "https://plugins.jetbrains.com/plugin/download?rel=true&updateId=595767" "php-impl/lib/php.jar"

# CSS https://plugins.jetbrains.com/plugin/22068-css/versions
download_and_extract "https://plugins.jetbrains.com/plugin/download?rel=true&updateId=587322" "css-impl/lib/css-impl.jar"

# JavaScript: https://plugins.jetbrains.com/plugin/22069-javascript-and-typescript/versions
download_and_extract "https://plugins.jetbrains.com/plugin/download?rel=true&updateId=595137" "javascript-plugin/lib/javascript-plugin.jar"

# JavaScript Intention Power Pack: https://plugins.jetbrains.com/plugin/264-javascript-intention-power-pack/versions
download_and_extract "https://plugins.jetbrains.com/plugin/download?rel=true&updateId=579601" "javascript-intentions/lib/javascript-intentions.jar"

# CoffeeScript https://plugins.jetbrains.com/plugin/6313-coffeescript/versions
download_and_extract "https://plugins.jetbrains.com/plugin/download?rel=true&updateId=579461" "coffeescript/lib/coffeescript.jar"

# Python https://plugins.jetbrains.com/plugin/7322-python-community-edition/versions
download_and_extract "https://plugins.jetbrains.com/plugin/download?rel=true&updateId=595111" "python-ce/lib/python-ce.jar"
