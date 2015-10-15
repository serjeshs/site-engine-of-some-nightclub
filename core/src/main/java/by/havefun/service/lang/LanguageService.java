package by.havefun.service.lang;

import java.util.HashMap;

import org.slf4j.LoggerFactory;

public class LanguageService {
    
    public static final int LANG_BEL = 1;
    public static final int LANG_ENG = 2;
    public static final int LANG_RUS = 3;
    

    public static java.util.Map<String, String> bel = new HashMap<String, String>();
    public static java.util.Map<String, String> eng = new HashMap<String, String>();
    public static java.util.Map<String, String> rus = new HashMap<String, String>();

    static {
        
        l("cost_min_int","Мінімальны кошт увахода лічбай","Min price int","Минимальная цена входа числом");
        l("cost_min_string",
                "Кошт з апісаннем, спасылкамі, дзе можна купіць квіткі, калі і якія.",
                "Price with a description , a link where you can buy the tickets when and what.",
                "Цена c описанием, сылками, где можно купить билеты, когда и какие.");
        l("description_event", "Апісанне падзеі", "Event description", "Описание события");
        l("end_time","Час канчатку","End time","Время окончания");
        l("hello_guest", "Вітаю, госць", "Hello, guest", "Привет, гость.");
        l("name_event", "Назва падзеі", "Name of event", "Название события");
        l("or", "альбо", "or", "или");
        l("place","Мейсца","Place","Место");
        l("please_enter_event_info", 
                "Увядзіце інфармацыю аб падзеі", 
                "Enter the information about the event", 
                "Введите информацию о событии");
        
        l("register_please", "", "register please", "");
        l("sing_in", "увайсці", "sing in", "войти");
        l("start_time","Час пачатку","Start time","Время начала");
        l("upload_new_image", 
                "Запампуйце новы малюнак, калі бягучага няма альбо яго трэба змяніць",
                "Upload a new image, if there is no current or need to change", 
                "Загрузите новое изображение, если текущего нету или его нужно изменить");

        l("", "", "", "");

    }

    private static void l(String key, String belarus, String english, String russan) {
        if (bel.get(key) != null) {
            LoggerFactory.getLogger("FAIL INIT").warn("KEY " + key + "DUBLICATE");
        } else {
            if (belarus.isEmpty() || english.isEmpty() || russan.isEmpty()) {

            } else {
                bel.put(key, belarus);
                eng.put(key, english);
                rus.put(key, russan);
            }
        }
    }
    
    public static java.util.Map<String, String> getLangMap(int lang) {
        switch (lang) {
        case LanguageService.LANG_BEL:
            return bel;
        case LanguageService.LANG_ENG:
            return eng;
        case LanguageService.LANG_RUS:
            return rus;
        default:
            return bel;
        }
    }

}
