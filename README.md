# Sponge Example Plugin
A good starting point for Sponge plugin developement. This repository is meant
as a guide to start your journey. This is still a draft and spellcheck is not
working in my editor, so I'm sorry about that.

### Structure
Knowing how a project is arranged can save so much time and confusion, so here
is how this one is arranged:

##### `src/main/java` 
The plugins soruce goes here. The main file for this project
is at `dev.genbyte.spongemvnex.SpongeMvnEx` so the full path to that file, from
the project root, would be
`src/main/java/dev/genbyte/spongemvnex/SpongeMvnEx.java`.

##### `src/resources`
The plugins resources go here. Stuff like the plugin.yml and a config.yml if you
have one.

If you take a look at the [plugin.yml][pluginyml], you'll see that all the keys
currently in that file have a value of `${keyName}`. That's because those are
variables that Maven will fill in when building the plugin. Look at the build
section of the pom.xml file for more information.

[pluginyml]: src/resources/plugin.yml

### Building
Building is done using maven because frankly I just prefer it (and I don't know
gradle). Have a look at the heavily commented [pom.xml][pomxml].

[pomxml]: pom.xml

There are two ways to build the plugin. The first is with your IDEs integration
with maven, if it has that. If not, you can run `mvn package` at the command
line. Both of these methods should put a `.jar` in the `target` directory.

### A quick note on CI
Before we move on to the meat of this document, The Main Class, I'd like to
mention CI. CI, in case you don't know, stands for Continuous Integration. Its
job is to try and build your code when you perform a certain action, like
pushing to the repostory. If the code fails to build, it notifies you in some
way to let you know.  I think [this][ci-article] is a good article on the
subject.

[ci-article]: https://codeship.com/continuous-integration-essentials

The specific CI used in this repository is the one offered by GitHub actions.
The setup of a CI for Java with Maven is detailed [here][github-ci].

[github-ci]: https://docs.github.com/en/free-pro-team@latest/actions/guides/building-and-testing-java-with-maven

### The Main Class
Here i'll try to explain the main plugin class as shwon below. The
[Sponge Docs][spongedocs-mainclass] do a better job, so maybe read
those instead.

[spongedocs-mainclass]: https://docs.spongepowered.org/stable/en/plugin/plugin-class.html

```java
// Imports exluded for brevity

@Plugin(id = "spongemvnex", name = "Sponge Maven Example", version = "1.0", description = "Maven Example")
public class SpongeMvnEx {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Successfully running the Sponge Maven Example Plugin!");
    }

}
```

Let's break that down.

The first line, well the first line that isn't a comment, is `@Plugin`. This
annotation tells Sponge that this is a plugin and that it should load it. The
annotation has some arguments, what do they mean? The `id` is, intuitively, the
id of the plugin. This is the main identification of the plugin and can probably
just be the name without spaces and all lowercase. Why Lowercase? See the
`Plugin Identifier` section below. Next is the `name` field which is just your
plugin name, `version` the plugin version, and `description` a short blurb
about what your plugin does. In theory these can be populated from our pom.xml
like we did with our plugin.yml, but I heard that was bad practice. I guess we
should just remember to increment the version when we make a release.

Oh god, that was a long paragraph. Do you need a break? I do.

Breaks over, sorry. Next is the `@Inject` annotation and the
`private Logger logger` line below it. Before I worked with the Sponge API,
I hardly used annotations. Really, it was like maybe once a class? Now I use
them all the time and life is better, I think. I again recommend you read the
Sponge Docs on [Dependency Injection][spongedocs-injection], but I will provide
my own short explanation (for posterity). When you put `@Inject` about a member
declartion, you're telling a library from Google (Sponge says its `Guice`, I
believe them) to shove an object of that type into that varaible. I think Sponge
sets up the injector with a bunch of types and that's where you get the logger
from. I don't know, I'm not all knowing.

[spongedocs-injection]: https://docs.spongepowered.org/stable/en/plugin/injection.html

What I do know, however, is what the next lines do. The `@Listener` annotation
lets Sponge know that the function it's annotating should be put into the event
loop. It will examine the type, in this case `GameStartedServerEvent`, and call
your method (`onServerStart`) when that event is processed. You can name the
method anything you want, but it typically describes the event it takes and
starts with `on`. It makes sense, really. "When does this function fire? It's
called `onServerStart` so I bet it runs when the server is started".

#### Plugin Identifier
The [Plugin Identifier][spongedocs-pluginidentifier] section of the Sponge Docs
says:

[spongedocs-pluginidentifier]: https://docs.spongepowered.org/stable/en/plugin/plugin-identifier.html

```
The plugin ID must be lowercase and start with an alphabetic character. It may
only contain alphanumeric characters, dashes or underscores. The plugin name
does not have such a limitation and can even contain spaces or special
characters. It cannot be longer than 64 characters.
```

It is also used as the name of the default configuration directory of your
plugin.