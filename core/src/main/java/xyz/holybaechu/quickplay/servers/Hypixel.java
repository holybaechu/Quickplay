package xyz.holybaechu.quickplay.servers;

import xyz.holybaechu.quickplay.utils.HypixelMinigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Hypixel {
    public List<HypixelMinigame> minigames = new ArrayList<>();

    final private String baseIconURL = "https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/";

    // https://hypixel.fandom.com/wiki/Commands#Play_Commands
    public Hypixel(){
        // Initalize minigames
        minigames.add(new HypixelMinigame().setName("SkyBlock").setModes(Map.ofEntries(Map.entry("Standard", "sb"))));
        minigames.add(new HypixelMinigame().setName("Warlords").setModes(Map.ofEntries(
                Map.entry("Capture the Flag", "warlords_ctf_mini"),
                Map.entry("Domination", "warlords_domination"),
                Map.entry("Team Deathmatch", "warlords_team_deathmatch")
        )));
        minigames.add(new HypixelMinigame().setName("Mega Walls").setModes(Map.ofEntries(
                Map.entry("Standard", "mw_standard"),
                Map.entry("Faceoff", "mw_face_off")
        )));
        minigames.add(new HypixelMinigame().setName("Blitz SG").setModes(Map.ofEntries(
                Map.entry("Solo", "blitz_solo_normal"),
                Map.entry("Teams", "mw_face_off")
        )).setImageURL("https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/SG-64.png"));
        minigames.add(new HypixelMinigame().setName("Skywars").setModes(Map.ofEntries(
                Map.entry("Solo Normal", "solo_normal"),
                Map.entry("Teams Normal", "teams_normal"),
                Map.entry("Solo Insane", "solo_insane"),
                Map.entry("Teams Insane", "teams_insane"),
                Map.entry("Mega", "mega_normal"),
                Map.entry("Mega Doubles", "mega_doubles"),
                Map.entry("Ranked", "ranked_normal"),
                Map.entry("Solo TNT Madness", "solo_insane_tnt_madness"),
                Map.entry("Doubles TNT Madness", "teams_insane_tnt_madness"),
                Map.entry("Solo Rush", "solo_insane_rush"),
                Map.entry("Doubles Rush", "teams_insane_rush"),
                Map.entry("Solo Slime", "solo_insane_slime"),
                Map.entry("Doubles Slime", "teams_insane_slime"),
                Map.entry("Solo Lucky Blocks", "solo_insane_lucky"),
                Map.entry("Doubles Lucky Blocks", "teams_insane_lucky"),
                Map.entry("Hunter vs. Beasts", "solo_insane_hunters_vs_beasts")
        )));
        minigames.add(new HypixelMinigame().setName("TNT Games").setModes(Map.ofEntries(
                Map.entry("TNT Run", "tnt_tntrun"),
                Map.entry("PVP Run", "tnt_pvprun"),
                Map.entry("Bow Spleef", "tnt_bowspleef"),
                Map.entry("TNT Tag", "tnt_tntag"),
                Map.entry("TNT Wizards", "tnt_capture")
        )).setImageURL("https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/TNT-64.png"));
        minigames.add(new HypixelMinigame().setName("Bed Wars").setModes(Map.ofEntries(
                Map.entry("Solo", "bedwars_eight_one"),
                Map.entry("4v4", "bedwars_two_four"),
                Map.entry("Doubles", "bedwars_eight_two"),
                Map.entry("3v3v3v3", "bedwars_four_three"),
                Map.entry("4v4v4v4", "bedwars_four_four"),
                Map.entry("Capture", "bedwars_capture"),
                Map.entry("Rush Solo", "bedwars_eight_one_rush"),
                Map.entry("Rush Doubles", "bedwars_eight_two_rush"),
                Map.entry("Rush 4v4v4v4", "bedwars_four_four_rush"),
                Map.entry("Ultimate Solo", "bedwars_eight_one_ultimate"),
                Map.entry("Ultimate Doubles", "bedwars_eight_two_ultimate"),
                Map.entry("Ultimate 4v4v4v4", "bedwars_four_four_ultimate"),
                Map.entry("Castle", "bedwars_castle"),
                Map.entry("Lucky Blocks Doubles", "bedwars_eight_two_lucky"),
                Map.entry("Lucky Blocks 4v4v4v4", "bedwars_four_four_lucky"),
                Map.entry("Voidless Doubles", "bedwars_eight_two_voidless"),
                Map.entry("Voidless 4v4v4v4", "bedwars_four_four_voidless"),
                Map.entry("Armed Doubles", "bedwars_eight_two_armed"),
                Map.entry("Armed 4v4v4v4", "bedwars_four_four_armed")
                // Couldn't find information about Underworld and Swappage mode
        )));
        minigames.add(new HypixelMinigame().setName("The Pit").setModes(Map.ofEntries(Map.entry("Standard", "pit"))).setImageURL("https://hypixel.net/styles/hypixel-v2/images/game-icons/Pit-64.png"));
        minigames.add(new HypixelMinigame().setName("Arcade").setModes(Map.ofEntries(
                Map.entry("Hole in the Wall", "arcade_hole_in_the_wall"),
                Map.entry("Football", "arcade_soccer"),
                Map.entry("Bounty Hunters", "arcade_bounty_hunters"),
                Map.entry("Pixel Painters", "arcade_pixel_painters"),
                Map.entry("Dragon Wars", "arcade_dragon_wars"),
                Map.entry("Ender Spleef", "arcade_ender_spleef"),
                Map.entry("Galaxy Wars", "arcade_starwars"),
                Map.entry("Throw Out", "arcade_throw_out"),
                Map.entry("Creeper Attack", "arcade_creeper_attack"),
                Map.entry("Party Games", "arcade_party_games_1"),
                Map.entry("Farm Hunt", "arcade_farm_hunt"),
                Map.entry("Zombies - Dead End", "arcade_zombies_dead_end"),
                Map.entry("Zombies - Bad Blood", "arcade_zombies_bad_blood"),
                Map.entry("Zombies - Alien Arcadium", "arcade_zombies_alien_arcadium"),
                Map.entry("Hide and Seek - Prop Hunt", "arcade_hide_and_seek_prop_hunt"),
                Map.entry("Hide and Seek - Party Pooper", "arcade_hide_and_seek_party_pooper"),
                Map.entry("Hypixel Says", "arcade_simon_says"),
                Map.entry("Mini Walls", "arcade_mini_walls"),
                Map.entry("Blocking Dead", "arcade_day_one"),
                Map.entry("Capture The Wool", "arcade_pvp_ctw"),
                // Pixel Party and Dropper is not on the wiki (I don't know why)
                Map.entry("Pixel Party", "arcade_pixel_party"),
                Map.entry("Dropper", "arcade_dropper")
        )));
        minigames.add(new HypixelMinigame().setName("Classic Games").setModes(Map.ofEntries(
                Map.entry("VampireZ", "vampirez"),
                Map.entry("Quake Solo", "quake_solo"),
                Map.entry("Quake Teams", "quake_teams"),
                Map.entry("Paintball", "paintball"),
                Map.entry("Arena 1v1", "arena_1v1"),
                Map.entry("Arena 2v2", "arena_2v2"),
                Map.entry("Arena 4v4", "arena_4v4"),
                Map.entry("The Walls", "walls"),
                Map.entry("Turbo Kart Racers", "tkr")
        )).setImageURL("https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/ClassicLobby-64.png"));
        minigames.add(new HypixelMinigame().setName("Cops and Crims").setModes(Map.ofEntries(
                Map.entry("Defusal", "mcgo_normal"),
                Map.entry("Deathmatch", "mcgo_deathmatch"),
                Map.entry("Defusal Party", "mcgo_normal_party"),
                Map.entry("Deathmatch Party", "mcgo_deathmatch_party"),
                Map.entry("Gun Game", "mcgo_gungame")
        )).setImageURL("https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/CVC-64.png"));
        minigames.add(new HypixelMinigame().setName("Build Battle").setModes(Map.ofEntries(
                Map.entry("Solo", "build_battle_solo_normal"),
                Map.entry("Teams", "build_battle_teams_normal"),
                Map.entry("Pro Mode", "build_battle_solo_pro"),
                Map.entry("Guess The Build", "build_battle_guess_the_build")
        )));
        minigames.add(new HypixelMinigame().setName("UHC").setModes(Map.ofEntries(
                Map.entry("Solo", "uhc_solo"),
                Map.entry("Teams", "uhc_teams"),
                Map.entry("Events Mode", "uhc_events")
        )));
        minigames.add(new HypixelMinigame().setName("Duels").setModes(Map.ofEntries(
            Map.entry("Bow Duel", "duels_bow_duel"),
            Map.entry("Mega Walls Duel", "duels_mw_duel"),
            Map.entry("UHC Four", "duels_uhc_four"),
            Map.entry("SkyWars Doubles", "duels_sw_doubles"),
            Map.entry("UHC Doubles", "duels_uhc_doubles"),
            Map.entry("UHC Duel", "duels_uhc_duel"),
            Map.entry("OP Doubles", "duels_op_doubles"),
            Map.entry("OP Duel", "duels_op_duel"),
            Map.entry("UHC Deathmatch", "duels_uhc_meetup"),
            Map.entry("Mega Walls Doubles", "duels_mw_doubles"),
            Map.entry("Classic Duel", "duels_classic_duel"),
            Map.entry("Potion Duel", "duels_potion_duel"),
            Map.entry("Combo Duel", "duels_combo_duel"),
            Map.entry("SkyWars Duel", "duels_sw_duel"),
            Map.entry("Blitz Duel", "duels_blitz_duel"),
            Map.entry("Spleef Duel", "duels_bowspleef_duel"),
            Map.entry("Sumo Duel", "duels_sumo_duel"),
            Map.entry("Bridge Duel", "duels_bridge_duel"),
            Map.entry("Bridge Doubles", "duels_bridge_doubles"),
            Map.entry("Bridge 3v3", "duels_bridge_threes"),
            Map.entry("Bridge 4v4", "duels_bridge_four"),
            Map.entry("Bridge 2v2v2v2", "duels_bridge_2v2v2v2"),
            Map.entry("Bridge 3v3v3v3", "duels_bridge_3v3v3v3"),
            Map.entry("Bridge CTF 3v3", "duels_capture_threes"),
            Map.entry("Boxing Duel", "duels_boxing_duel"),
            Map.entry("Parkour", "duels_parkour_eight"),
            Map.entry("Duel Arena", "duels_duel_arena")

        )));
        minigames.add(new HypixelMinigame().setName("Speed UHC").setModes(Map.ofEntries(
                Map.entry("Solo Normal", "speed_solo_normal"),
                Map.entry("Team Normal", "speed_team_normal")
        )));
        minigames.add(new HypixelMinigame().setName("Smash Heroes").setModes(Map.ofEntries(
                Map.entry("Solo 1v1v1v1", "super_smash_solo_normal"),
                Map.entry("Teams 2v2", "super_smash_2v2_normal"),
                Map.entry("Teams 2v2v2", "super_smash_teams_normal"),
                Map.entry("1v1 Mode", "super_smash_1v1_normal"),
                Map.entry("Friends 1v1v1v1", "super_smash_friends_normal")
        )));
        minigames.add(new HypixelMinigame().setName("Murder Mystery").setModes(Map.ofEntries(
                Map.entry("Classic", "murder_classic"),
                Map.entry("Double Up", "murder_double_up"),
                Map.entry("Assassins", "murder_assassins"),
                Map.entry("Infection", "murder_infection")
        )));
        minigames.add(new HypixelMinigame().setName("Wool Wars").setModes(Map.ofEntries(Map.entry("Standard", "wool_wool_wars_two_four"))).setImageURL("https://hypixel.net/styles/hypixel-v2/images/game-icons/WoolWars-64.png"));
        // Couldn't find information about SMP, Housing, WoolWars
    }
}
