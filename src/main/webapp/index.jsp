<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demander mon crédit en ligne</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}static/css/style.scss">
</head>

<body>
<div class="container">
    <div class="header">
        <h1>Demander mon crédit en ligne</h1>
        <a href="#" class="back-link">← Retour</a>
    </div>
    <section>
        <div class="steps-content">
            <div class="steps">
                <div class="step active"><span>1</span> Simuler mon crédit</div>
                <div class="step"><span>2</span>Mes coordonnées</div>
                <div class="step"><span>3</span>Mes infos personnelles</div>
            </div>

            <div class="content-wrapper">
                <section class="loan-form">
                    <form action="#" id="loan-form">
                        <div class="form-group">
                            <label for="project">Mon projet</label>
                            <select id="project">
                                <option value="personal-loan">J'ai besoin d'argent</option>
                                <option value="personal-loan">Je finance mon véhicule d’occasion</option>
                                <option value="personal-loan">Je Gère mes imprévus</option>
                                <option value="personal-loan">Je finance mon véhicule neuf</option>
                                <option value="personal-loan">J’équipe ma maison</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="profession">Je suis</label>
                            <select id="profession">
                                <option value="fonctionnaire">Fonctionnaire</option>
                                <option value="fonctionnaire">Salarié du secteur privé</option>
                                <option value="fonctionnaire">Profession libérale</option>
                                <option value="fonctionnaire">Commerçant</option>
                                <option value="fonctionnaire">Artisan</option>
                                <option value="fonctionnaire">Retraité</option>
                                <option value="fonctionnaire">Autres professions</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="amount">Montant (en DH)</label>
                            <div class="input-wrapper">
                                <input type="number" id="amount" value="10000" min="1000" max="50000">
                                <input type="range" id="amount-range" value="10000" min="1000" max="50000">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="duration">Durée (en mois)</label>
                            <div class="input-wrapper">
                                <input type="number" id="duration" value="24" min="12" max="60">
                                <input type="range" id="duration-range" value="24" min="12" max="60">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="monthly">Mensualités (en DH)</label>
                            <div class="input-wrapper">
                                <input type="number" id="monthly" value="482.95" readonly>
                                <input type="range" id="monthly-range" value="482.95" min="100" max="1000" step="0.01" disabled>
                            </div>
                        </div>

                        <button type="submit" class="btn-submit">Continuer<br><span>Sans engagement</span></button>
                    </form>
                </section>

            </div>
        </div>
        <aside class="recap">
            <h2>Mon récapitulatif</h2>
            <div class="recap-content">
                <p class="title-recap">Mon projet</p>
                <p class="recap-personel"><strong>Prêt Personnel</strong></p>
            </div>
        </aside>

    </section>


</div>

<script src="js/app.js"></script>
<script src="${pageContext.request.contextPath}static/js/app.js"></script>
</body>

</html>