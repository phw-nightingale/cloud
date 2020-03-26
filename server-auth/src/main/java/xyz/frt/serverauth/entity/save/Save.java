package xyz.frt.serverauth.entity.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/3/25 下午6:24
 *
 * 存档文件
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Save {

    public Configs configs = new Configs(
            false,
            "./res",
            false,
            true,
            "./res/img/wallpapers/bg1.jpg",
            true,
            false,
            true,
            1,
            1519442460788L,
            8,
            9,
            94,
            "rgba(2,35,64,1)",
            true,
            new Configs.Wallpaper[]{
                    new Configs.Wallpaper("./res/img/wallpapers/bg1.jpg", "./res/img/wallpapers/bg1_1.jpg"),
                    new Configs.Wallpaper("./res/img/wallpapers/bg2.jpg", "./res/img/wallpapers/bg2_1.jpg"),
                    new Configs.Wallpaper("./res/img/wallpapers/bg3.jpg", "./res/img/wallpapers/bg3_1.jpg")
            },
            false,
            true
    );

    public Map<String, App> apps;

    public Shortcut[] shortcuts;

    public Tile[] tiles;

    public StartMenu startMenu;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Configs {

        public boolean topTaskBar;
        public String pathRes;
        public boolean sound;
        public boolean shortcutsSortAuto;
        public String wallpaper;
        public boolean wallpaperBlur;
        public boolean wallpaperSlide;
        public boolean wallpaperSlideRandom;
        public int wallpaperSlideItv;
        public long wallpaperSlideTime;
        public int wallpaperSlideIndex;
        public int openMax;
        public int idCounter;
        public String themeColor;
        public boolean autoThemeColor;
        public Wallpaper[] wallpapers ;
        public boolean debug;
        public boolean winBlur;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Wallpaper {
            public String image;
            public String preview;
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class App {

        public boolean addressBar;
        public int autoRun;
        public boolean background;
        public int badge;
        public String desc;

        public Icon icon;
        public String openMode;
        public boolean plugin;

        public Position position;
        public String powerBy;
        public boolean resizable;
        public boolean single;

        public Size size;
        public String title;
        public String url;
        public String customTile;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Size {
            public String width;
            public String height;
        }


        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Position {
            public String x;
            public String y;
            public boolean left;
            public boolean top;
            public boolean autoOffset;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Icon {
            public String type;
            public String bg;
            public String content;

        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Shortcut {
        public String app;
        public String title;
        public Map<String, Object> params;
        public String hash;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Tile {

        public String title;
        public Data[] data;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        static class Data {
            public int x;
            public int y;
            public int w;
            public int h;
            public String app;
            public String title;
            public String i;
            public Map<String, Object> params;
            public String hash;
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class StartMenu {

        public Shortcut[] sideBar;
        public Shortcut[] menu;

    }

}
