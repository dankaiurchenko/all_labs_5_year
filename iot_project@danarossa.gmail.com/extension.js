/* St = Shell Toolkit */
const St = imports.gi.St;
const PanelMenu = imports.ui.panelMenu;
const Gio = imports.gi.Gio;
const Main = imports.ui.main;
const GLib = imports.gi.GLib;
const Mainloop = imports.mainloop;

let dndButton;
let globalStillListeningFlag;
let configMap = {}

const Me = imports.misc.extensionUtils.getCurrentExtension();
imports.searchPath.unshift(Me.path);

function init() {
}

function thisPath(name) {
    return Me.path + "/" + name;
}


function enable() {
    readConfigFile();
    dndButton = new PanelMenu.Button(1, "DoNotDisturb", false);

    let box = new St.BoxLayout();
    let icon = new St.Icon({
        style_class: "system-status-icon",
    });
    icon.set_gicon(Gio.icon_new_for_string(thisPath("mic.svg")));
    box.add(icon);

    dndButton.actor.add_child(box);
    Main.panel.addToStatusArea("DoNotDisturbRole", dndButton, 0, "right");

    // Menu
    let dndMenu = dndButton.menu;
    const snLabel = new St.Label({
        text: 'text',
        style: "margin: 10px; font-size: large",
    });

    // PopupMenu expects all children have _delegate
    snLabel._delegate = null;
    dndMenu.box.add(snLabel);
    dndMenu.connect("open-state-changed", function (menu, isOpen) {
            globalStillListeningFlag = isOpen;
            if (isOpen) {
                snLabel.set_text("Listening...");
                myLoopFunction(snLabel);
            }
        }
    );
}

function disable() {
    dndButton.destroy();
}


function readConfigFile() {
    let file = Gio.file_new_for_path(thisPath("main.config"));
    file.load_contents_async(null, function (file, res) {
        let contents;
        try {
            contents = file.load_contents_finish(res)[1].toString();
            let commands = contents.split('\n').map(el => el.trim()).filter(el => el.length > 0 && el.indexOf(':') > -1);
            commands.forEach(command => {
                    let a = command.split(':');
                    let a1 = a[0].trim();
                    let a2 = a[1].trim();
                    if (a1 && a2) {
                        configMap[a1.toLowerCase()] = a2;
                    }
                }
            )

        } catch (error) {
            print(error)
        }
    });
}

function listen(snLabel) {
    let [res, out] = GLib.spawn_command_line_sync("python2.7 " + thisPath("main.py"));
    snLabel.set_text(out.toString());
    let s = out.toString().toLowerCase().trim();
    let configMapElement = configMap[s];
    if (globalStillListeningFlag) {
        if (configMapElement) {
            GLib.spawn_command_line_async(configMapElement);
        } else {
            snLabel.set_text("Try once again");
            myLoopFunction(snLabel);
        }
    }
}

let myLoopFunction = (snLabel) => {
    Mainloop.timeout_add(500, () => {
            snLabel.set_text("Listening...");
            if (globalStillListeningFlag) {
                Mainloop.timeout_add(500, () => listen(snLabel));
            }
        }
    );
}
