// Merchant script
// Makes taterzen exchange (barter for) items, specified
// in `global_item_map`.
// Taterzen must have a `merchant` trait (`/trait scarpet add merchant`)
//
// v1.1.0
// author: samo_lego


// Requirements
__config() -> {
  'requires' -> {
    'carpet' -> '>=1.4.33',
    'minecraft' -> '>=1.17',
    'taterzens' -> '>=1.6.0'
  },
  'stay_loaded' -> true
};

// Item conversion map
// Modify to your needs
global_item_map = {
    // Takes 32 stone and gives 2 gold
    'stone' -> {
        'take_count'-> 32,
        'trade_item'-> 'gold',
        'trade_count'-> 2
    },
    // Takes 16 oak_log(s) and gives 3 emerald(s)
    'oak_log' -> {
        'take_count'-> 16,
        'trade_item'-> 'emerald',
        'trade_count'-> 3
    }
    // etc.
};


// Event function
__on_taterzen_tries_pickup(taterzen, traits, item) -> (
    // Only execute if taterzen has 'merchant' trait
    if( (traits ~ 'merchant' == null),
        return();
    );

    // Get the item id of the 'item'
    [thrown_item, thrown_size, _id] = item ~ 'item';

    // Check if we can trade
    trading_item = get(global_item_map, thrown_item);

    if( (trading_item != null),
            (
                // How many items you get per dropped 'item'
                payment_size = get(trading_item, 'trade_count');
                take_size = get(trading_item, 'take_count');

                // Saving the original throw size to use it below
                thrown_size_orig = thrown_size;

                // How many of the items should be returned
                thrown_size = thrown_size_orig % take_size;

                // How many 'item-packs' have we traded
                size_multiplier = floor(thrown_size_orig / take_size);

                // Sets the nbt for the 'payment' item
                nbt = {
                  'Item' -> {
                    'id' -> get(trading_item, 'trade_item'),
                    'Count' -> size_multiplier * payment_size
                  },
                  'Motion' -> (query(taterzen, 'look') + 0.1) * 0.2,
                  'PickupDelay'-> 20
                };

                // If thrown size was modified (we took some items)
                // spawn in the payment item
                if(thrown_size != thrown_size_orig,
                    spawn('item', taterzen ~ 'pos' + [0.0, 1.4, 0.0], nbt(nbt));
                );
            )
    );

    // Push the remaining stack away
    old_stack_nbt = {
        'Item' -> {
            'Count' -> thrown_size
        },
        'Motion' -> (query(taterzen, 'look') + 0.000001) * 0.1
    };

    // Modify thrown item nbt
    modify(item, 'nbt_merge', old_stack_nbt);
);
