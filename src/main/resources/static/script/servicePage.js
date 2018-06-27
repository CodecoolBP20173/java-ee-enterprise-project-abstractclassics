
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
});