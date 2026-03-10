(() => {
    const apiBase = "api/v1/hello";

    const form = document.getElementById("hello-form");
    const nameInput = document.getElementById("name");
    const resultEl = document.getElementById("result");
    const btnDefault = document.getElementById("btn-default");

    async function fetchDefault() {
        await requestJson(`${apiBase}`);
    }

    async function submitName(name) {
        await requestJson(apiBase, {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8"
            },
            body: JSON.stringify({ name })
        });
    }

    async function requestJson(url, options = {}) {
        try {
            const response = await fetch(url, options);
            const data = await response.json();
            resultEl.textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            resultEl.textContent = `请求失败: ${error.message}`;
        }
    }

    form.addEventListener("submit", async (event) => {
        event.preventDefault();
        const name = nameInput.value.trim();
        await submitName(name);
    });

    btnDefault.addEventListener("click", fetchDefault);

    fetchDefault();
})();
