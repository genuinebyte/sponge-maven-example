# Sponge Example Plugin
A good starting point for Sponge plugin developement. This repository is meant
as a guide to start your journey.

### Structure
Knowing how a project is arranged can save so much time and confusion, so here
is how this one is arranged:

#### `src/main/java` 
The plugins soruce goes here. The main file for this project
is at `dev.genbyte.spongemvnex.SpongeMvnEx` so the full path to that file, from
the project root, would be
`src/main/java/dev/genbyte/spongemvnex/SpongeMvnEx.java`.

#### `src/resources`
The plugins resources go here. Stuff like the plugin.yml and a config.yml if you
have one.

### Building
Building is done using maven because frankly I just prefer it (and I don't know
gradle). Have a look at the heavily commented [pom.xml][pomxml].

There are two ways to build the plugin. The first is with your IDEs integration
with maven, if it has that. If not, you can run `mvn install` at the command
line. Both of these methods should put a `.jar` in the `target` directory.