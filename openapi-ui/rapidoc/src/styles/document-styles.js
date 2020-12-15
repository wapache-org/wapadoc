import { css } from 'lit-element';

export default css`

#api-document {
  width:100%;
  height:100%;
  color: #000;
  background-color:#eee;
  font-size: 10.5pt;
}

#api-document * {
  /* Include the content box as well as padding and border for precise definitions */
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  font-family: Microsoft YaHei, SimSun;
}

/** 导航栏 开始 */
#api-document-navbar {
  width:400px;
  height:100%;
  overflow: auto;
  float: left;
  box-shadow: rgba(75, 75, 75, 0.2) 0px 4px 5px;
  padding: 10px;
  background-color: #fff;
}

#api-document-navbar ul, 
#api-document-navbar ol, 
#api-document-navbar li {
  list-style:none;
  padding-left: 6px;
}

#api-document-navbar li {
  color: #000;
  line-height: 24px;
}

#api-document-navbar h1 a {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

#api-document-navbar li a {
  display:block;
  width:100%;
  color: #000;
  height: 24px;
  line-height: 24px;
  text-decoration: none;
  cursor: pointer;
  padding-left: 6px;
}

#api-document-navbar li a:hover {
  background-color:#eee;
}

#api-document-navbar a:link,
#api-document-navbar a:visited,
#api-document-navbar a:hover,
#api-document-navbar a:active,
#api-document-navbar a:link,
#api-document-navbar a:visited,
#api-document-navbar a:hover,
#api-document-navbar a:active {
  color: #000;
}


/** 导航栏 结束 */

#api-document-content {
  height:100%;
  overflow: auto;
}

/* word页面效果相关样式 开始 */

.api-document-page {
  /* Define a white paper background that sticks out from the darker overall background */
  background: #fff;
  /* Show a drop shadow beneath each page */
  box-shadow: 0 4px 5px rgba(75, 75, 75, 0.2);
  /* Override outline from user agent stylesheets */
  outline: 0;
  /* width: 650px; */
}

.api-document-page.paper-a4 {
  /* Divide single pages with some space and center all pages horizontally */
  margin: 1cm auto;

  /* A4 */

  /* Styles for better appearance on screens only -- are reset to defaults in print styles later */

  /* Reflect the paper width in the screen rendering (must match size from @page rule) */
  width: 21cm;
  /* Reflect the paper height in the screen rendering (must match size from @page rule) */
  min-height: 29.7cm;

  /* Reflect the actual page margin/padding on paper in the screen rendering (must match margin from @page rule) */
  padding-left: 2cm;
  padding-top: 2cm;
  padding-right: 2cm;
  padding-bottom: 2cm;
}

/* Use CSS Paged Media to switch from continuous documents to sheet-like documents with separate pages */
@page {
  /* You can only change the size, margins, orphans, widows and page breaks here */

  /* Require that at least this many lines of a paragraph must be left at the bottom of a page */
  orphans: 4;
  /* Require that at least this many lines of a paragraph must be left at the top of a new page */
  widows: 2;

  /* You can only change the size, margins, orphans, widows and page breaks here */

  /* Paper size and page orientation */
  size: A4 portrait;

  /* Margin per single side of the page */
  margin-left: 2cm;
  margin-top: 2cm;
  margin-right: 2cm;
  margin-bottom: 2cm;
}

/* Defines a class for manual page breaks via inserted .page-break element */
.api-document-page div.page-break {
  page-break-after: always;
}

/* Simulates the behavior of manual page breaks from \`print\` mode in \`screen\` mode */
@media screen {
  /* Renders the border and shadow at the bottom of the upper virtual page */
  .api-document-page div.page-break::before {
    content: "";
    display: block;
    /* Give a sufficient height to this element so that its drop shadow is properly rendered */
    height: 0.8cm;
    /* Offset the negative extra margin at the left of the non-pseudo element */
    margin-left: 0.5cm;
    /* Offset the negative extra margin at the right of the non-pseudo element */
    margin-right: 0.5cm;
    /* Make the bottom area appear as a part of the page margins of the upper virtual page */
    background-color: #fff;
    /* Show a drop shadow beneath the upper virtual page */
    box-shadow: 0 6px 5px rgba(75, 75, 75, 0.2);
  }
  /* Renders the empty space as a divider between the two virtual pages that are actually two parts of the same page */
  .api-document-page div.page-break {
    display: block;
    /* Assign the intended height plus the height of the pseudo element */
    height: 1.8cm;
    /* Apply a negative margin at the left to offset the page margins of the page plus some negative extra margin to paint over the border and shadow of the page */
    margin-left: -2.5cm;
    /* Apply a negative margin at the right to offset the page margins of the page plus some negative extra margin to paint over the border and shadow of the page */
    margin-right: -2.5cm;
    /* Create the bottom page margin on the upper virtual page (minus the height of the pseudo element) */
    margin-top: 1.2cm;
    /* Create the top page margin on the lower virtual page */
    margin-bottom: 2cm;
    /* Let this page appear as empty space between the virtual pages */
    background: #eee;
  }
}
/* For top-level headings only */
.api-document-page h1 {
  /* Force page breaks after */
  page-break-before: always;
}
/* For all headings */
.api-document-page h1, .api-document-page h2, .api-document-page h3, .api-document-page h4, .api-document-page h5, .api-document-page h6 {
  /* Avoid page breaks immediately */
  page-break-after: avoid;
}
/* For all paragraph tags */
.api-document-page p {
  /* Reset the margin so that the text starts and ends at the expected marks */
  margin: 0;
}
/* For adjacent paragraph tags */
.api-document-page p + p {
  /* Restore the spacing between the paragraphs */
  margin-top: 0.5cm;
}
/* For links in the document */
.api-document-page a {
  /* Prevent colorization or decoration */
  text-decoration: none;
  color: black;
}
/* For tables in the document */
.api-document-page table {
  /* Avoid page breaks inside */
  page-break-inside: avoid;
}

/* word页面效果相关样式 结束 */


#api-document h1, 
#api-document h2, 
#api-document h3, 
#api-document h4 
{
  font-weight: bold;
}

.api-document-page table {
  margin: 10px 0;
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  /* background-color: #fff;
  color: #666; */
}

.api-document-page table caption {
  margin: 0.5em;
}

.api-document-page table td,
.api-document-page table th {
  border: 1px solid #000;
  padding: 3px 6px;
}

.api-document-page table thead tr {
  background-color: #eee;
  font-weight: bold;
}

.api-document-page table tbody tr:hover {
  background-color: #fafafa;
}

/* .api-document-page table td, 
.api-document-page table th 
{
  position: relative;
  padding: 9px 15px;
  min-height: 20px;
  line-height: 20px;
  font-size: 14px; 
} */

.api-document-page table th,
.api-document-page table td.header-column
{
  text-align: left;
  font-weight: bold;
}

.api-document-page .width-2em
{
  width:2em;
}
.api-document-page .width-3em
{
  width:3em;
}
.api-document-page .width-4em
{
  width:4em;
}
.api-document-page .width-5em
{
  width:5em;
}
.api-document-page .width-6em
{
  width:6em;
}

#api-table-of-content::-webkit-scrollbar,
#database-table-of-content::-webkit-scrollbar {/*滚动条整体样式*/
  width: 10px;     /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}

#api-table-of-content::-webkit-scrollbar-thumb,
#database-table-of-content::-webkit-scrollbar-thumb {/*滚动条里面小方块
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);*/
  background: #1F3056;
}

#api-table-of-content::-webkit-scrollbar-track,
#database-table-of-content::-webkit-scrollbar-track {/*滚动条里面轨道
  -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
  border-radius: 10px;*/
  background: #2F4056;
}


#api-toc-search {
  position: fixed;
  left: 0;
  top: 0;
  width: 25%;
  height: 50px;
  background-color: #2F4056;
}

#database-table-of-content #api-toc-search
{
  width: 300px;
}

#api-toc-search-input {
  position: fixed;
  left: 12px;
  top: 12px;
  width: 24%;
  height: 26px;
}

#database-table-of-content #api-toc-search-input
{
  width: 276px;
}

#api-toc-root {
  margin: 50px 0;
  padding: 10px 0;
}

#api-header
{
  padding-bottom: 40px;
}

/*
#database-document
{
  margin-left: 300px;
}


#database-header
, #database-table-groups
{
  margin: auto;
}

#database-table-groups .database-table-group h2 {
  border-bottom: 1px solid #e6e6e6;
}
*/
`;
