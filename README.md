# TaterzenScarpetProfessions

Professions for [Taterzens](https://github.com/samolego/Taterzens) powered by [Scarpet lang](https://github.com/gnembon/fabric-carpet/wiki/Scarpet).

## How-to

0. Install fabric-carpet and taterzens mods.
1. Download the script professions you want and put them in `world/scripts`
2. Run the server.
3. Make sure the script is loaded (`/script load <script name>`). (To autoload the scripts, use `/carpet setDefault scriptsAutoload true`)
4. Give taterzen the `taterzens:scarpet_profession` (`/npc edit professions add taterzens:scarpet_profession`).
5. Set the scarpet traits if needed (`/trait scarpet add <trait name>`).
6. Enjoy and modify scripts to your needs.

## Developers

Feel free to fork the repo and add any scripts for taterzens!

#### What is the `trait` parameter?

Let's assume you have two taterzens; `foo` and `bar`. `bar` can trade use items using [`merchant`](https://github.com/samolego/TaterzenScarpetProfessions/blob/master/scripts/merchant.sc) script and `foo` prints the picked up item name with `printitem.sc`
(both respond to `__on_taterzen_tries_pickup` event).

Since a taterzen can only have 1 `scarpet_profession`, you can't distinguish whether an event was triggered by a taterzen that should
respond to it or not - e.g. when `bar` picks up an item
1. The `__on_taterzen_tries_pickup` is triggered.
2. Both event listeners (`merchant.sc` and `printitem.sc`) get called.

How can we execute only the `merchant.sc` event on `bar`?
Here's where traits come in place. In your script, check if taterzen has an e.g. `merchant` trait.
If it's not present, just use `return()`.
```python
if( (traits ~ 'merchant' == null),
    return();  // 'merchant' trait not present
);
```


## Available events

See [github source page](https://github.com/samolego/Taterzens/blob/master/fabric/src/main/java/org/samo_lego/taterzens/compatibility/carpet/ScarpetProfession.java).
All events start with `__on_<event name>`.

You can also use the events below to add them in [IDEA highlighting](https://github.com/gnembon/fabric-carpet/blob/master/docs/scarpet/resources/editors/idea/Idea.md).
```python
__on_taterzen_tries_pickup(taterzen, traits, item) ->
__on_taterzen_interacted(taterzen, traits, item) ->
__on_taterzen_is_attacked(taterzen, traits, attacker) ->
__on_taterzen_movement_ticks(taterzen, traits) ->
__on_taterzen_removed(taterzen, traits) ->
__on_taterzen_nbt_loaded(taterzen, traits, nbt) ->
__on_taterzen_nbt_saved(taterzen, traits, nbt) ->
__on_taterzen_movement_set(taterzen, traits, movement_type) ->
__on_taterzen_taterzen_behaviour_set(taterzen, traits, behaviour_type) ->
__on_taterzen_tries_ranged_attack(taterzen, traits, target_entity) ->
__on_taterzen_tries_melee_attack(taterzen, traits, target_entity) ->
__on_taterzen_approached_by(taterzen, traits, player_list) ->
```
