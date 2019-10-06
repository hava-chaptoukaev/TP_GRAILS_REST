<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-annonce" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.annonce}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.annonce}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

            <g:form enctype="multipart/form-data" resource="${this.annonce}" method="POST">
                    <fieldset class="form">
                        <div class="fieldcontain required">
                            <label for="title">Title
                                <span class="required-indicator">*</span>
                            </label><input type="text" name="title" value="${annonce.title}" required="" id="title">
                        </div>
                        <div class="fieldcontain required">
                            <label for="description">Description
                                <span class="required-indicator">*</span>
                            </label><input type="text" name="description" value="${annonce.description}" required="" id="description">
                        </div>
                        <div class="fieldcontain required">
                            <label for="validTill">Valid Till
                                <span class="required-indicator">*</span>
                            </label><input type="hidden" name="validTill" value="date.struct">
                            <select name="validTill_day" id="validTill_day" aria-labelledby="validTill"><option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3" selected="selected">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                            </select>
                            <select name="validTill_month" id="validTill_month" aria-labelledby="validTill"><option value="1">janvier</option>
                                <option value="2">février</option>
                                <option value="3">mars</option>
                                <option value="4">avril</option>
                                <option value="5">mai</option>
                                <option value="6">juin</option>
                                <option value="7">juillet</option>
                                <option value="8">août</option>
                                <option value="9">septembre</option>
                                <option value="10" selected="selected">octobre</option>
                                <option value="11">novembre</option>
                                <option value="12">décembre</option>
                            </select>
                            <select name="validTill_year" id="validTill_year" aria-labelledby="validTill"><option value="2119">2119</option>
                                <option value="2050">2050</option>
                                <option value="2049">2049</option>
                                <option value="2048">2048</option>
                                <option value="2047">2047</option>
                                <option value="2046">2046</option>
                                <option value="2045">2045</option>
                                <option value="2044">2044</option>
                                <option value="2043">2043</option>
                                <option value="2042">2042</option>
                                <option value="2041">2041</option>
                                <option value="2040">2040</option>
                                <option value="2039">2039</option>
                                <option value="2038">2038</option>
                                <option value="2037">2037</option>
                                <option value="2036">2036</option>
                                <option value="2035">2035</option>
                                <option value="2034">2034</option>
                                <option value="2033">2033</option>
                                <option value="2032">2032</option>
                                <option value="2031">2031</option>
                                <option value="2030">2030</option>
                                <option value="2029">2029</option>
                                <option value="2028">2028</option>
                                <option value="2027">2027</option>
                                <option value="2026">2026</option>
                                <option value="2025">2025</option>
                                <option value="2024">2024</option>
                                <option value="2023">2023</option>
                                <option value="2022">2022</option>
                                <option value="2021">2021</option>
                                <option value="2020">2020</option>
                                <option value="2019" selected="selected">2019</option>
                            </select>
                        </div>
                        <div class="fieldcontain required">
                                <label>Illustrations
                                    <span class="required-indicator">*</span>
                                </label>
                                <div class="property-value" aria-labelledby="illustrations-label">
                                        <g:each in="${annonce.illustrations}" var="illustration">
                                            <li>
                                                <g:link title="Delete illustration" controller="annonce" action="deleteIllustration" id="${illustration.id}" params="[idAnnonce: annonce.id]">
                                                    <g:img file="${illustration.filename}" width="50" height="50" />
                                                </g:link>
                                            </li>
                                        </g:each>
                            </div>
                        </div>
                        <div class="fieldcontain required">
                            <label for="illustrations">Add illustrations
                                <span class="required indicator">*</span>
                            </label><input type="file" name="file" id="illustrations" multiple="multiple" accept="image/png">
                        </div>
                        <div class="fieldcontain">
                            <label for="state">Enable</label><input type="hidden" name="_state"><input type="checkbox" name="state" id="state">
                        </div>
                    </fieldset>
                    <fieldset class="buttons">
                        <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </fieldset>
            </g:form>
        </div>
    </body>
</html>
