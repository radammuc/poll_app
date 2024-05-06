const LOCATION_URL = window.location.href;

const BASE_URL= LOCATION_URL.substring(0, LOCATION_URL.lastIndexOf("/")+1);

export const get = async (path: string) => {
    console.log('BASE_URL', BASE_URL);

    console.log('href', window.location.href);
    console.log('path', window.location.pathname);

    return fetch(BASE_URL + path, {
        headers: {
            'Accept': 'application/json, text/plain'
        }
    })
}

export const remove = async (path: string, data: any) => {
    return fetch(BASE_URL + path, {
        method: 'DELETE',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json'
        }
    });
}

export const post = async (path: string, data: any) => {
    return fetch(BASE_URL + path, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json'
        }
    });
}

export const put = async (path: string, data: any) => {

    return fetch(BASE_URL + path, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json'
        }
    });
}