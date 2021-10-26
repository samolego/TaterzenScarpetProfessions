# TaterzenScarpetProfessions

Professions for [Taterzens](https://github.com/samolego/Taterzens) powered by [Scarpet lang](https://github.com/gnembon/fabric-carpet/wiki/Scarpet).

## How-to

0. Install fabric-carpet and taterzens mods.
1. Download the script professions you want and put them in `world/scripts`
2. Run the server.
3. Make sure the script is loaded (`/script load <script name>`). (To autoload the scripts, use `/carpet setDefault scriptsAutoload true`)
4. Give taterzen the `taterzens:scarpet_profession` (`/npc edit professions add taterzens:scarpet_profession`).
5. Enjoy and modify scripts to your needs.

## Developers

Feel free to fork the repo and add any scripts for taterzens!

## Available events

See [github source page](https://github.com/samolego/Taterzens/blob/master/fabric/src/main/java/org/samo_lego/taterzens/compatibility/carpet/ScarpetProfession.java).
All events start with `__on_<event name>`.
