grammar SlideDeckML;

// -----------------
// Parser rules
// -----------------

file
  : deckDecl EOF
  ;

deckDecl
  : 'deck' deckName=STR '{' deckItem* '}'
  ;

deckItem
  : headDecl
  | templateDecl
  | layoutDecl
  | slideDecl
  ;

headDecl
  : 'head' '{' headProp* '}'
  ;

headProp
  : 'theme' value ';'
  | 'title' value ';'
  | 'author' value ';'
  | 'exportFilename' value ';'
  | 'mdc' value ';'
  | 'defaults' '{' slideFrontProp* '}'
  ;

templateDecl
  : 'template' templateName=ID '{' templateProp* '}'
  ;

templateProp
  : 'cssPath' value ';'
  | 'assetsDir' value ';'
  | 'logoPaths' '[' valueList? ']' ';'
  ;

layoutDecl
  : 'layout' layoutName=ID '{'
      ('kind' layoutKind ';')?
      ('zones' '{' layoutZone* '}' )?
    '}'
  ;

layoutKind
  : 'STRUCTURED'
  | 'FLEX'
  | 'FREE'
  ;

layoutZone
  : 'zone' zoneName=ID '{'
      'role' area ';'
      ('defaultOrder' INT ';')?
    '}'
  ;

area
  : 'HEADER'
  | 'CONTENT'
  | 'LEFT'
  | 'RIGHT'
  | 'FOOTER'
  | 'CANVAS'
  ;

slideDecl
  : 'slide' (slideId=ID)? '{'
      slideFrontProp*
      slideBodyItem*
    '}'
  ;

slideFrontProp
  : 'title' value ';'
  | 'layout' ID ';'
  | 'class' value ';'
  | 'transition' transitionValue ';'
  | 'notes' value ';'
  ;

transitionValue
  : ID ( '|' ID )?
  | '{' transitionObjProp* '}'
  ;

transitionObjProp
  : ID ':' value ';'
  ;

slideBodyItem
  : slotDecl
  | elementDecl
  | stepDecl
  | markdownDecl
  ;

slotDecl
  : 'slot' slotName=ID ';'
  ;

markdownDecl
  : 'markdown' value ';'
  ;

elementDecl
  : 'element' elementId=ID '{'
      ('type' elementType ';')?
      elementProp*
    '}'
  ;

elementType
  : 'TextBlock'
  | 'ListBlock'
  | 'ImageElement'
  | 'VideoElement'
  | 'CodeBlock'
  | 'EquationBlock'
  ;

elementProp
  : 'content' value ';'
  | 'ordered' value ';'
  | 'src' value ';'
  | 'altText' value ';'
  | 'fit' imageFit ';'
  | 'autoplay' value ';'
  | 'loop' value ';'
  | 'muted' value ';'
  | 'controls' value ';'
  | 'language' value ';'
  | 'displayMode' equationDisplayMode ';'
  | 'latexSource' value ';'
  | 'zIndex' INT ';'
  | 'slotOf' ID ';'
  | positionDecl
  | animationDecl
  ;

positionDecl
  : 'position' (absolutePosition | relativePosition)
  ;

absolutePosition
  : 'absolute' '{'
      'x' number ';'
      'y' number ';'
      ('width' number ';')?
      ('height' number ';')?
      ('unit' unit ';')?
      ('anchor' anchorType ';')?
    '}'
  ;

relativePosition
  : 'relative' '{'
      ('alignX' align ';')?
      ('alignY' align ';')?
      ('orderInZone' INT ';')?
      ('inZone' ID ';')?
    '}'
  ;

animationDecl
  : 'animation' '{'
      'kind' animationKind ';'
      ('delayMs' INT ';')?
      ('durationMs' INT ';')?
    '}'
  ;

animationKind
  : 'APPEAR'
  | 'FADE_IN'
  | 'FADE_IN_SLIDE_IN'
  | 'ZOOM_IN'
  ;

unit
  : 'PERCENT'
  | 'PX'
  ;

anchorType
  : 'TOP_LEFT'
  | 'TOP_CENTER'
  | 'CENTER'
  | 'BOTTOM_CENTER'
  | 'BOTTOM_RIGHT'
  ;

align
  : 'START'
  | 'CENTER'
  | 'END'
  ;

imageFit
  : 'CONTAIN'
  | 'COVER'
  | 'STRETCH'
  ;

equationDisplayMode
  : 'INLINE'
  | 'BLOCK'
  ;

stepDecl
  : 'step' stepIndex=INT '{' stepItem* '}'
  ;

stepItem
  : 'reveal' ID ';'
  | 'codeReveal' ID range ';'
  | 'switch' ID 'show' ID ';'
  ;

range
  : INT '..' INT
  ;

valueList
  : value (',' value)*
  ;

value
  : STR
  | MLSTR
  | number
  | bool
  | ID
  | array
  | obj
  ;

array
  : '[' (value (',' value)*)? ']'
  ;

obj
  : '{' (ID ':' value ';')* '}'
  ;

number
  : FLOAT
  | INT
  ;

bool
  : 'true'
  | 'false'
  ;

// -----------------
// Lexer rules
// -----------------

MLSTR
  : '"""' ( . | '\r' | '\n' )*? '"""'
  ;

STR
  : '"' ( '\\' . | ~["\\\r\n] )* '"'
  ;

FLOAT
  : [0-9]+ '.' [0-9]+
  ;

INT
  : [0-9]+
  ;

ID
  : [a-zA-Z_][a-zA-Z0-9_-]*
  ;

LINE_COMMENT
  : '//' ~[\r\n]* -> skip
  ;

BLOCK_COMMENT
  : '/*' ( . | '\r' | '\n' )*? '*/' -> skip
  ;

WS
  : [ \t\r\n]+ -> skip
  ;

BOM
  : '\uFEFF' -> skip
  ;
