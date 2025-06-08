export default function getNavigateUrl(roleList) {
    let url = '/';
    if (roleList.includes('ADMIN')) {
        url = '/admin'
    } else if (roleList.includes('MANAGER')) {
        url = '/manager'
    } else {
        url = '/member';
    }
    return url;
}
