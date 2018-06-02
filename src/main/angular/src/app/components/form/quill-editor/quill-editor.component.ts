import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-quill-editor',
  templateUrl: './quill-editor.component.html',
  styleUrls: ['./quill-editor.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class QuillEditorComponent implements OnInit {

  public editor;
  public editorContent = `<h3>I am Example 02</h3>`;
  public editorConfig = {
    placeholder: "输入公告内容，支持html"
  };

  constructor() {}

  onEditorBlured(quill) {
    console.log('editor blur!', quill);
  }

  onEditorFocused(quill) {
    console.log('editor focus!', quill);
  }

  onEditorCreated(quill) {
    this.editor = quill;
    console.log('quill is ready! this is current quill instance object', quill);
  }

  onContentChanged({ quill, html, text }) {
    console.log('quill content is changed!', quill, html, text);
  }

  ngOnInit() {
    setTimeout(() => {
      this.editorContent = '<h1>Example 02 changed!</h1>';
      console.log('you can use the quill instance object to do something', this.editor);
      // this.editor.disable();
    }, 2800)
  }
}
