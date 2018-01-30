import {EventRelevant} from "./eventRelevant";
import {EventGallery} from "./eventGallery";
import {Event} from "./event";

export class MainEventPage {
  today: Event[];
  tomorrow: Event[];
  currentAndNextWeek: Event[];
  currentMonth: Event[];
  nextMonth: Event[];
  nextNextMonth: Event[];
  relevant: EventRelevant[];
  gallery: EventGallery[];
}


