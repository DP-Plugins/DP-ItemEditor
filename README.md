<center><img src="https://i.postimg.cc/MKPVVR1s/dplogo-512.png" alt="logo"></center>
<center><img src="https://i.postimg.cc/RZ9dqPFx/introduce.png" alt="introduce"></center>

This plugin allows server administrators to easily **edit and customize items directly in-game**.  
Change item names, lore, enchantments, and attributes without restarting the server or editing files manually.

---

<center><img src="https://i.postimg.cc/RZ9dqP08/description.png" alt="description"></center>

- Edit **item display names** directly via command  
- Add, remove, clear, and list **custom lore lines**  
- Add or remove **enchantments** with custom levels  
- Manage **vanilla item attributes** such as attack damage or health  
- Supports **English and Korean language files**  
- Works instantly in-game without server restart  

---

<center><img src="https://i.postimg.cc/rwcjzhpH/depend-plugin.png" alt="depend-plugin"></center>

- All DP-Plugins require the **`DPP-Core`** plugin  
- The plugin will not work if **`DPP-Core`** is not installed  
- You can download **`DPP-Core`** here: <a href="https://github.com/DP-Plugins/DPP-Core/releases" target="_blank">Click me!</a>  

---

<center><img src="https://i.postimg.cc/dV01RxJB/installation.png" alt="installation"></center>

1️⃣ Place the **`DPP-Core`** plugin and this plugin file (**`DP-ItemEditor-*.jar`**) into your server’s **`plugins`** folder  

2️⃣ Restart the server, and the plugin will be automatically enabled  

3️⃣ Language files will be generated automatically (`en_US`, `ko_KR`)  

---

<center><img src="https://i.postimg.cc/jSKcC85K/settings.png" alt="settings"></center>

- **`config.yml`**  
  - Minimal configuration (no required settings)  
- **Language files**  
  - Edit message text in `lang/en_US.yml` or `lang/ko_KR.yml` if needed  

---

<center><img src="https://i.postimg.cc/SxqdjZKw/command.png" alt="command"></center>

❗ Some commands require appropriate permissions (admin usage recommended)

**Command List and Examples**

| Command | Description | Example |
|---|---|---|
| `/dpie name <text>` | Set item display name | `/dpie name &6Epic Sword` |
| `/dpie lore add <text>` | Add lore line | `/dpie lore add &7Legendary Item` |
| `/dpie lore remove <line>` | Remove lore line | `/dpie lore remove 1` |
| `/dpie lore clear` | Clear all lore | `/dpie lore clear` |
| `/dpie lore list` | List lore lines | `/dpie lore list` |
| `/dpie enchant add <enchant> <level>` | Add enchantment | `/dpie enchant add sharpness 5` |
| `/dpie enchant remove <enchant>` | Remove enchantment | `/dpie enchant remove sharpness` |
| `/dpie enchant list` | List enchantments | `/dpie enchant list` |
| `/dpie attribute add <attribute> <amount>` | Add attribute | `/dpie attribute add GENERIC_ATTACK_DAMAGE 5` |
| `/dpie attribute remove <attribute>` | Remove attribute | `/dpie attribute remove GENERIC_ATTACK_DAMAGE` |
| `/dpie attribute list` | List attributes | `/dpie attribute list` |

**❗Notes when using commands**

- Commands edit the **item in the player’s hand**  
- Attribute and enchant names must be valid Minecraft identifiers  
- Permissions are required for each command node  
- Changes apply instantly  

---

<center><img src="https://i.postimg.cc/Z5ZH0fqL/api-integration.png" alt="api-integration"></center>

- No external API integrations are used  

---

<center><a href="https://discord.gg/JnMCqkn2FX"><img src="https://i.postimg.cc/4xZPn8dC/discord.png" alt="discord"></a></center>

- https://discord.gg/JnMCqkn2FX  
- If you have any questions or issues, please contact your server administrator or join our Discord  
- Feature suggestions and feedback are always welcome!

---
