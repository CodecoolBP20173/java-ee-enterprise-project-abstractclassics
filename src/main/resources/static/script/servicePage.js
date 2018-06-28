
$(document).ready(function() {
    let prismfeet = document.getElementsByClassName("face ft");

    for (let i = 0; i < prismfeet.length; i++) {
        prismfeet[i].addEventListener('click', function () {
            let prismScene = prismfeet[i].parentNode.parentNode;
            let con1 = prismScene.parentNode.parentNode;
            prismScene.classList.add('scene_turn');
            con1.classList.add('con1_turn')

        })
    }

    let backButtons = document.getElementsByClassName("back_button");
    for (let i = 0; i <backButtons.length; i++) {
        backButtons[i].addEventListener('click', function () {
            let prismScene = backButtons[i].parentNode.parentNode.parentNode;
            let con1 = prismScene.parentNode.parentNode;
            prismScene.classList.remove('scene_turn');
            con1.classList.remove('con1_turn')
        })
    }
    let contactButtons = document.getElementsByClassName("contact_button");
    for (let i = 0; i < contactButtons.length; i++) {
        contactButtons[i].addEventListener('click', function () {
            let prismScene = contactButtons[i].parentNode.parentNode.parentNode;
            prismScene.classList.add('scene_turn_left');
        })
    }

    let backFromContact = document.getElementsByClassName("back_to_deatils_button");
    for (let i = 0; i < backFromContact.length; i++) {
        backFromContact[i].addEventListener('click', function () {
            let prismScene = contactButtons[i].parentNode.parentNode.parentNode;
            prismScene.classList.remove('scene_turn_left');
        })
    }


    let ratingDivs = document.getElementsByClassName("starContainer");
    for (let i = 0; i < ratingDivs.length; i++) {
        let ratingDiv = ratingDivs[i];
        let rating = ratingDiv.getAttribute("data-avgRating");
        let roundedRating = Math.round(rating);

        let stars= ratingDiv.getElementsByClassName("fa fa-star");
        console.log(rating);
        for( let i = 0; i < roundedRating; i++) {
            stars[i].classList.add("checked");
        }
    }

    let rateContainers = document.getElementsByClassName("rate_container");
    let ratedServiceIdList = document.getElementsByClassName("serviceContainer").item(0).getAttribute('data-ratedServiceList');
    for (let i = 0; i < rateContainers.length; i++) {
        let stars= rateContainers[i].getElementsByClassName("fa fa-star");
        let rateContainer = rateContainers[i];
        let rating = rateContainer.getAttribute("data-avgRating");
        let roundedRating = Math.round(rating);
        if ($.inArray(rateContainer.getAttribute('data-serviceId'), ratedServiceIdList) !== -1){
            rateContainer.classList.add('disabled');
            let messageDiv = document.createElement("div");
            let message = document.createTextNode("You have already rated this user!");
            messageDiv.classList.add("hasRatedDiv");
            messageDiv.appendChild(message);
            rateContainer.parentNode.appendChild(messageDiv);
            for (let j = 0; j < roundedRating; j++) {
                stars[j].classList.add("checked")
            }
        } else {
            for (let j = 0; j < stars.length; j++) {
                let star = stars[j];
                star.addEventListener('mouseenter', function (evt) {
                    for (let k = 0; k <= j; k++) {
                        stars[k].classList.add("checked")
                    }
                });
                star.addEventListener('mouseleave', function (evt) {
                    for (let k = 0; k <= j; k++) {
                        stars[k].classList.remove("checked")
                    }
                });
                star.addEventListener('click', function (evt) {
                    let rating = 0;
                    for (let k = 0; k <= j; k++) {
                        stars[k].classList.add("final_checked");
                        rating++;
                    }
                    let parentDiv = star.parentNode;
                    parentDiv.classList.add('disabled');
                    let serviceId = parentDiv.getAttribute('data-serviceId');
                    console.log(rating);
                    jQuery.ajax({
                        url: 'list/rating',
                        method: 'POST',
                        data: {
                            rating: rating,
                            serviceId: serviceId
                            //Todo send user UserId
                        },
                        error: function (xhr, desc, err) {
                            console.log(xhr);
                            console.log("Details0: " + desc + "\nError:" + err);
                        }
                    });
                });


            }
        }
    }

});