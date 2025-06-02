🧭 Pokemon Game?

It’s a personal project, a proving ground, and a collection of "that’ll do" code solutions held together by sheer force of will and caffeine.

🎮 What's This All About?

Think: top-down pixel adventure, minimal graphics, maximal learning.
I'm using this game to explore:

Java game loops
Map handling
Collision systems
Entity updates
Object interactions
Crying when NullPointerException appears in the console
This is not a polished indie title destined for Steam (yet). It's where I try out new mechanics, mess with architecture, and learn by doing (and debugging... a lot of debugging).

🧪 Features (Subject to Spontaneous Breakage)

🚶 Player movement via keyboard
🗺️ Tile-based map rendering
🎯 Collision detection (with optional rage when it fails)
🎒 Inventory and item interaction framework
🧱 Modular code with a sprinkle of organized chaos
🛠️ Built With

Java (the beverage of choice for most IDEs)
IntelliJ IDEA (because I enjoy the pain of 500-line stack traces in a friendly UI)
🚀 Getting Started

Requirements
Java 8+ (or something close enough)
IntelliJ (or your IDE of choice — if you're feeling brave)
Patience
Run Instructions
Clone the repo:
git clone https://github.com/gjfranics9/AdventureGame.git
Open in IntelliJ (File > Open > point to the directory).
Run the Main class.
Watch a window appear. Be amazed.
🗂️ Project Structure (Roughly Speaking)

AdventureGame/
├── src/
│   ├── main/            # Actual game code lives here
│   └── test/            # Some JUnit tests for when I'm feeling responsible
├── res/                 # Player sprites and map files
└── pokemonProject.iml   # Shhh... it's a legacy name
🎮 Controls

Arrow keys: Move around
E (eventually): Interact (probably)
ESC: Exit, when it’s all too much
🧠 Why This Exists

I wanted to build something that lets me:

Apply OOP principles to a real(-ish) project
Break large problems into smaller bugs
Practice test writing (because apparently that’s important)
Have fun making something entirely mine
And if it helps someone else learn along the way — or makes them laugh at my method names — even better.

💡 TODO (aka the Wish List)

A battle system, this is one that will take a while but is arguably the most important feature
An entire database of different pokemon, each pokemon should be able to have multiple instances so that you can actually use and store them, also different characteristics for unqiue pokemon - more research into their uniqueness needed
Saving state is important. The data I feel needs to be fixed is whats been interacted with so far - what pokemon the character has and the state they are in, the items held and the position in the map.
Usable items - there are multiple types here like the key items, usable items, and in battle items. Storing them all in a bag under different sections works for me
NPCs - this is not of as much of a priority to me, I would rather focus on the main aspects
Interactable tiles such as the water tiles and item tiles - this is probably a sooner rather than later one
Any UI features (Who knows), a bag/settings/pokedex menu as well as a menu to start the game
Multiple maps with connecting portals, eventually hopefully connecting to an entire region/map
I also really hate the way I've handled placing items on the map, instead of loading an individual file in pair with each map I think a better idea is to store them all in one json file with a list of objects under the key of the map ID


It’s a solo project for now, but feel free to fork, star, laugh, or steal code responsibly. If you’ve got ideas or spot bugs, pull requests are welcome (no promises I won’t break it again later).

📜 License

MIT — because sharing is caring.

I used RyiSnow's Java 2D game development tutorial to help with a lot of the code but I've added my fair share of unique mechanics and designs - hopefully will diverge from it more and more (That will take a lot more time)

Made with Java, mild despair, and a genuine love of learning.
— George
