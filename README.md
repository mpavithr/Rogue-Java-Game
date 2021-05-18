# Rogue-Java-Game
Terminal video game in Java.

The ascii display is the third-party asciiPanel code in Java. The ascii display only knows about characters, and displays them. It is
driven by the logical display which knows about the objects and what characters should be displayed for
them, and where they should be displayed. 

The logical display: Game objects are displayed on the logical display, which is called the ObjectDisplayGrid in my code. It
manages the displayable objects at each location in the ascii display, and sends the character
corresponding to each visible object to the ascii display. It also receives keyboard input, and is
implements the Subject role in the Observer pattern.
The logical display has 5 areas.
A top message area, where the remaining hit points and the score are displayed. The number of lines in
this top area is given in the XML file.
The game area of the display – this is the largest area, and immediately follows the top message area.
The XML file gives its height.
The bottom message area, where the inventory is written and the info messages (discussed below) are
written. About half of the bottom message area should be used for inventory, and half for info
messages.
The top and bottom message areas only display text.
The width of the logical display is also given in the XML file.
The ascii display is made large enough to display all elements written to the logical display can be shown
on the ascii display.

The Dungeon:
The dungeon will be defined in an XML file. There are multiple XML files and these XML files give the layout of the dungeon, where
monsters, the player, weapons, armor, and scrolls can be found. It defines the properties of
weapons, monsters, armor and scrolls, i.e., how much damage they inflict or protect us from, and the
actions of monsters and players when they are hit, move or die. By parsing the XML file, I get the 
information needed to create the objects that represent the different parts of the dungeon. 
The Dungeon also maintains lists of coordinates in the dungeon that are traversable.
The Dungeon also implements the Observer role in the Observer pattern, and receives notifications from
the logical display when commands are entered. The Dungeon operates on three of those commands:
Help: ‘?’: show the different commands in the info section of the display
Help ‘H’ <command>give more detailed information about the specified command in the info section of
the display.
End game ‘E’ <Y | y>: end the game.

Creatures in the game:
There are two kinds of creatures in the game dungeon, monsters and the player. Different creature
actions can be attached to the monsters and player to customize how they respond to being hit, when
moving, and when dying. There are 3 kinds of monsters and a player. Trolls are represented by a ‘T’,
Snakes by an ‘S’ and Hobgoblins by an ‘H’. The player is represented by an ‘@’.

Properties of creatures:
Creatures have health specified as hitpoints (i.e., how much more damage they can get before dying),
maxHit, a maximum number of hitpoints that can be delivered when attacking a creature, a type, which
is the character that represents the creature, a string that is the name of the creature, and lists of
actions (see below) that are described in the XML file that will be performed in case of death or being
hit. Players also hap a score, how many moves have to be made to get a single hitpoint added to their
health, and a pack that holds a list of items. Each creature also has an X and Y coordinate that indicates
their position on the game display.

Built-in actions for creatures:
Creatures always have the following actions:
Initialize the display: put themselves on the display in the correct position at game startup.
Refresh the display: refresh the display during the play of the game.
Perform the actions specified in the XML file as associated with being hit.
The damage delivered when hitting: this is a random number between 0 and maxHit, inclusive.
Perform the actions specified in the XML file as associated with dying.

Additional built-in actions for players:
Players have all of the built-in actions of a creature, plus the following additional actions.
Move (left, right, up, down): move to a new location. The move can only occur if the new location is
traversable, i.e., is a dungeon floor or passageway. If the location to be moved to is occupied by another
creature, the other creature is hit but the creature moving does not occupy that location. If the location 
to be moved to is occupied by an item (armor, a sword or a scroll) the creature moves to that location.
A separate command is used to actually pick up the item, and only the player creature pics up items.
As well, the XML file can specify move actions that are performed at each move.

Show or display the inventory ‘I’: show the contents of the pack, printing the name for each item in the
pack. Each item is preceded by an integer 1 … max items in the pack that is used to refer to the item
when removing or dopping items in the pack.

Change, or take off armor ‘c’: armor that is being worn is taken off and placed in the pack. If not armor
is being worn a message should be shown in the info area of the game display.

Drop ‘d’ <integer>: drop item <integer> from the pack. If the <integer> does not refer to an item in the
pack and informational message is printed on the game display in the info area.
  
Read an item ‘r’ <integer>: the item specified by the integer must be a scroll that is in the pack. It causes
the scroll to perform its actions.
  
Pick up an item from the dungeon floor ‘p’: pick up the visible item on the dungeon floor location that
the player is standing on. If multiple items are in the location, only the top item is picked up.
  
Take out a weapon ‘T’ <integer>: take the sword identified by <integer> from the pack. If the identified
item is not a sword, or no such item exists, show a message in the info area of the game display.
  
Wear item ‘w’ <integer>: take the armor identified by <integer> from the pack and make it the armor
being worn. If the identified item is not armor, or no such item exists, show a message in the info area
of the game display.
  
Actions that can be added to creatures:
Creatures will have the following actions attached to them:
Print: causes a message to be printed to the console (not the game display).
Teleport: causes the creature to go to a random place in the dungeon that is legal for a creature to be,
i.e., within a room or a passageway between room. A creature should not end up outside of the
traversable part of the dungeon.
UpdateDisplay: causes the display to be refreshed.
YouWin: This is typically executed by a monster that is killed. It updates the player’s points.
In addition to these actions, Players also have the following actions:
DropPack: drop a single item from the pack. The dropped item should be placed on the floor and be
available to be picked up at a later time.
EmptyPack: drop all items from the pack. The dropped items should be placed on the floor and be
available to be picked up at a later time.
EndGame: typically executed when the player dies, it causes the game to be ended and all further input
to be ignored.
  
Items in the game:
The game dungeon contains the following items: armor, scrolls and swords. Armor absorbs damage and
protects the wearer (who is always the player in our game) from some of the damage inflicted by a
monster. All items can be picked up by the player and stored in the pack. Armor can be worn, and
swords can be wielded, by the player. Scrolls sit in the pack and are read by the player, causing them to
take effect. Scrolls can only be read once.
All items have a string that is the name, and is what is written for the item when the player performs an
inventory command.
The following characters are used to represent items: A weapon (i.e, a sword in our game) is ")", armor
is "]", and a scroll is "?".
  
Actions for items:
The following items can be associated with items.
Bless/curse item: reduces the effectiveness of a sword or armor. Usually attached to a scroll, and
blesses or curses when the scroll is read.
Hallucinate: it registers with the observer and when invoked causes each item within the dungeon to
display a different character with each move of the player. This lasts for a limited number of moves,
with the number of moves being set in the XML file.
Hallucinate implements the Observer interface of the Observer pattern, and registers with the Dungeon.
It is notified of each input command, and keeps tracks of the number of moves so that hallucination
stops after the specified number of moves.
When it is invoked it will print out a message in the info area of the game display that hallucinations will
continue for some number of moves.
  
Structural elements of the dungeon:
The dungeon consists of Rooms and Passages. Rooms consist of walls (which are not traversable and are
represented by an ‘X’) and floors (which are traversable and represented by a ‘.’). Passages consist of
passage floors, which are represented by a ‘#’ and junctions, which is where the passage floor runs into
a room wall, and are represented as a ‘+’. Passage floors and junctions are traversable. Items and
creatures may be placed on room floors, passage floors and passage junctions. They may not be placed
on room walls.
  
In the XML file defining a dungeon, items and creatures are always initially placed on a room floor. The
coordinates of the item are relative to the upper left corner of the room, i.e., they are not absolute
coordinates in the game display. Coordinates for rooms and passages are relative to the game area of
the display, not the uppermost and leftmost corner of the ascii display.
Displaying messages in the message areas:
Because all objects placed on the logical display must have the same base type, a special type that holds
a character must be used for displaying messages in the top and bottom message display areas.
  
A summary of commands:
These have been discussed above. This section exists to get all of the commands that are input by the
user of the game in one section.
  
Commands processed by the dungeon:
  
?: help. Lists all commands in this section, but only gives the letter, not information about what the
command does, e.g. “h,l,k,j,i,?,H,c,d,p,R,T,w,E,0-9. H <cmd> for more info”
  
H <next input character>: <next input character> is a command. H gives more detailed information
about the command.
  
E <next input character>: If <next input character> is a ‘Y’ or ‘y’, the game is ended. If it is any other
character, the game continues.
  
Move commands, processed by the Player and code needing to count moves.
Move commands are the vi/vim navigation keys.
  
h: move left 1 space.
  
l: move right 1 space. Note that this is a lower case el, l, not an upper case i.
  
k: move up 1 space
  
j: move down 1 space
  
Move commands, processed by the Player
  
i: inventory -- show pack contents
  
c: take off/change armor
  
d: drop <item number> item from pack";
  
p: pick up item under player and put into the pack";
  
r <item in pack number>: read the scroll which is item number <item in pack number> in pack
  
t: take out weapon from pack
  
w: take out the armor which is item number <item number in pack> in the pack and put it on.
